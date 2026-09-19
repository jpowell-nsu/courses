#!/usr/bin/env bash
# ============================================================================
#  walkthrough-script.sh
#  Click through companion for the Chapters 6 and 7 walkthrough.
#  Every command is shown on screen before it runs, so you never have to
#  type or read from paper during class. Press Enter whenever the script
#  waits for you.
#
#  Rehearse first, with nothing touched on the machine:
#      DRY_RUN=1 ./walkthrough-script.sh
#
#  Run it for real, on the demo machine, during class:
#      ./walkthrough-script.sh
#
#  This only needs to run on the instructor's machine. Students follow the
#  walkthrough PDF and type Parts 1 through 4 themselves on their own
#  machines. Part 5 is the one shared demo everyone watches together.
# ============================================================================

set -euo pipefail
DRY_RUN="${DRY_RUN:-0}"

pause() {
  read -rp "$1" _
}

banner() {
  echo
  echo "============================================================"
  echo " $1"
  echo "============================================================"
}

# Shows a command, waits for Enter, then runs it. Works for single lines
# or multi-line blocks passed as one string.
run_step() {
  local desc="$1"
  local cmd="$2"
  local allow_fail="${3:-0}"
  echo
  echo "# ${desc}"
  while IFS= read -r line; do
    echo "\$ ${line}"
  done <<< "${cmd}"
  pause "Press Enter to run this..."
  if [[ "${DRY_RUN}" == "1" ]]; then
    echo "[dry run, nothing executed]"
  elif [[ "${allow_fail}" == "1" ]]; then
    eval "${cmd}" || true
  else
    eval "${cmd}"
  fi
}

# Same as run_step, but captures the command's stdout into the variable
# name given as the third argument, so a later step can reuse it.
run_step_capture() {
  local desc="$1"
  local cmd="$2"
  local var_name="$3"
  local placeholder="$4"
  echo
  echo "# ${desc}"
  echo "\$ ${cmd}"
  pause "Press Enter to run this..."
  if [[ "${DRY_RUN}" == "1" ]]; then
    printf -v "${var_name}" '%s' "${placeholder}"
    echo "[dry run, nothing executed, using placeholder ${!var_name}]"
  else
    printf -v "${var_name}" '%s' "$(eval "${cmd}")"
    echo "-> ${!var_name}"
  fi
}

# Holds a prediction question on screen before the command is even shown.
predict() {
  echo
  echo "PREDICT. $1"
  pause "Once the group has answered out loud, press Enter to reveal the command..."
}

echo "Chapters 6 and 7 walkthrough, click through script."
echo "Distribution on this machine should be Arch, per the earlier test run."
pause "Press Enter to begin..."

# ----------------------------------------------------------------------
banner "PART 1: DIMM INVENTORY, LIVE"
# ----------------------------------------------------------------------

run_step "List the installed DIMMs" \
"sudo dmidecode --type 17"

# ----------------------------------------------------------------------
banner "PART 2: ERROR CORRECTION, LIVE"
# ----------------------------------------------------------------------

run_step "Check the Error Correction Type field" \
'sudo dmidecode --type 16 | grep -A1 "Error Correction"'

run_step "Check for live EDAC error counters" \
"ls /sys/devices/system/edac/mc/"

# ----------------------------------------------------------------------
banner "PART 3: SPINNING OR SOLID STATE, LIVE"
# ----------------------------------------------------------------------

run_step "Label every attached drive as rotational or not" \
"lsblk -d -o name,rota,size,model"

# ----------------------------------------------------------------------
banner "PART 4: THE I/O SCHEDULER, LIVE"
# ----------------------------------------------------------------------

run_step "Check the active scheduler for every block device" \
'for d in /sys/block/*/queue/scheduler; do echo "$d"; cat "$d"; done'

# ----------------------------------------------------------------------
banner "PART 5: RAID LEVEL 1, BUILT AND BROKEN"
# ----------------------------------------------------------------------

echo
echo "Reminder: rotate who is at the keyboard here. Whoever is not typing"
echo "explains the output back to the group before you move on."
pause "Press Enter to continue..."

run_step "Create the demo folder, two 256 MB backing files, and look at them" \
"sudo mkdir -p /root/raid-demo
sudo fallocate -l 256M /root/raid-demo/disk1.img
sudo fallocate -l 256M /root/raid-demo/disk2.img
ls -lh /root/raid-demo/"

echo
echo "A loop device lets the kernel treat an ordinary file as if it were a"
echo "physical disk, the same idea behind mounting an ISO. The numbers in"
echo "/dev/loop0 and /dev/loop1 just mean these were the first two loop"
echo "slots the kernel had free, nothing more."
pause "Press Enter to continue..."

run_step_capture "Attach disk1.img as a loop device" \
"sudo losetup --find --show /root/raid-demo/disk1.img" LOOP1 "/dev/loop0"

run_step_capture "Attach disk2.img as a loop device" \
"sudo losetup --find --show /root/raid-demo/disk2.img" LOOP2 "/dev/loop1"

run_step "Confirm which file backs each loop device" \
"losetup -a"

echo
echo "These two devices, ${LOOP1} and ${LOOP2}, stand in for two physical disks."
pause "Press Enter to continue..."

predict "Two independent files are about to become one mirrored array. What do you expect the two loop devices to contain once this finishes, identical data, split data, or something else?"

echo
echo "Two prompts may appear during the next command."
echo "  - Write-intent bitmap: answer N, or press Enter. Not needed for this demo."
echo "  - Metadata not suitable as a boot device, continue creating array: answer y."
echo "    N or Enter here aborts the array entirely, which is not what you want."
pause "Press Enter to continue..."

run_step "Build the RAID 1 mirror and check its status" \
"sudo mdadm --create /dev/md0 --level=1 --raid-devices=2 ${LOOP1} ${LOOP2}
cat /proc/mdstat
lsblk"

run_step "Format the array, mount it, write a file, and look at the mount" \
'sudo mkfs.ext4 /dev/md0
sudo mkdir -p /mnt/raid-demo
sudo mount /dev/md0 /mnt/raid-demo
echo "Chapter 7, Level 1, written once and stored twice" | sudo tee /mnt/raid-demo/message.txt
df -h /mnt/raid-demo
ls -l /mnt/raid-demo/'

run_step "Read the raw bytes straight out of both backing files" \
'sudo grep -a "Chapter 7" /root/raid-demo/disk1.img
sudo grep -a "Chapter 7" /root/raid-demo/disk2.img'

predict "One of the two loop devices is about to disappear from the array on purpose. What do you expect to happen to message.txt, and why?"

run_step "Fail one member of the array and check the file" \
"sudo mdadm /dev/md0 --fail ${LOOP1}
cat /proc/mdstat
cat /mnt/raid-demo/message.txt"

run_step "Check both backing files again, after the failure" \
'sudo grep -a "Chapter 7" /root/raid-demo/disk1.img
sudo grep -a "Chapter 7" /root/raid-demo/disk2.img'

echo
echo "Discuss before moving on: what would have happened here on a Level 0"
echo "array instead, per Table 7.3's Data Availability column?"
pause "Press Enter to clean up..."

run_step "Clean up, tear everything down, and confirm it is gone" \
"sudo umount /mnt/raid-demo
sudo mdadm --stop /dev/md0
sudo losetup -d ${LOOP1}
sudo losetup -d ${LOOP2}
sudo rm -rf /root/raid-demo /mnt/raid-demo
ls /root/raid-demo/" 1

banner "DONE. Wrap up and connect to the next session."
