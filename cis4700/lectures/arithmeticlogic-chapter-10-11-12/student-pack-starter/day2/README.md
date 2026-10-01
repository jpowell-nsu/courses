# Day 2 -- Logic, Gates, and XOR

Theme: "XOR is the gate where logic meets cryptography."

## Challenge A: single-byte XOR
File: `cipher2A.hex`.

- In CyberChef, load the bytes and try "XOR Brute Force" (or reason about the
  XOR truth table and try keys yourself).

- One key turns the noise into English. The flag is inside.

## Challenge B: the reused key
Files: `cipher2B_1.hex`, `cipher2B_2.hex`, and `msg1_known.txt`.

- Two messages were encrypted by XORing each with a key.

- We have already recovered message 1 (see `msg1_known.txt`), but 
was unable to capture the key at that time.

- Recover the key from message 1 and its ciphertext, then use it on the
  second ciphertext. The flag is in message 2.

## Challenge C: the Logic Lock
File: `logiclock` (source: `logiclock.c`).

- Run it: `./logiclock`

- It reads one 32-bit key. Only four bits matter. Brute force is not the way.

- Hint: Open it in Ghidra or radare2, read which bits are tested and how they are
  combined into the gate output, write the truth table, and pick any key whose
  four bits satisfy the function.
