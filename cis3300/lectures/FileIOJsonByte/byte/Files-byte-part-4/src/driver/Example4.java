package driver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Demo 4 of 4 for byte files. Saves a whole roster of crew members
 * to outpost.save, then loads it back. A binary file has no natural way
 * to mark "the array ends here," so we write how many crew members are
 * coming (an int) before the crew member records themselves, then read
 * that many records back on the way in. The same idea shows up again,
 * one level deeper, for each crew member's own skill list.
 *
 * @author  Dr. Powell
 * @version 1.0
 */
public class Example4 {

    /** Writes one crew member's fields, in a fixed order, to an open stream. */
    private static void writeCrewMember(DataOutputStream out, CrewMember member) throws IOException {
        out.writeUTF(member.getName());
        out.writeUTF(member.getRole());
        out.writeInt(member.getHealth());
        out.writeDouble(member.getMorale());
        out.writeBoolean(member.isOnDuty());

        ArrayList<String> skills = member.getSkills();
        out.writeInt(skills.size());
        for (String skill : skills) {
            out.writeUTF(skill);
        }
    }

    /** Reads one crew member's fields, in the same fixed order, from an open stream. */
    private static CrewMember readCrewMember(DataInputStream in) throws IOException {
        String name = in.readUTF();
        String role = in.readUTF();
        int health = in.readInt();
        double morale = in.readDouble();
        boolean onDuty = in.readBoolean();

        CrewMember member = new CrewMember(name, role, health, morale, onDuty);

        int skillCount = in.readInt();
        for (int i = 0; i < skillCount; i++) {
            member.addSkill(in.readUTF());
        }
        return member;
    }

    public static void main(String[] args) {
        ArrayList<CrewMember> roster = new ArrayList<CrewMember>();

        CrewMember priya = new CrewMember("Priya Nair", "Engineer", 92, 88.5, true);
        priya.addSkill("Reactor Maintenance");
        priya.addSkill("Spacewalk Certified");
        roster.add(priya);

        CrewMember dax = new CrewMember("Dax Okonkwo", "Botanist", 78, 65.0, false);
        dax.addSkill("Hydroponics");
        roster.add(dax);

        CrewMember lin = new CrewMember("Lin Sato", "Medic", 100, 95.0, true);
        lin.addSkill("Field Surgery");
        lin.addSkill("Radiation Treatment");
        roster.add(lin);

        try (DataOutputStream out = new DataOutputStream(new FileOutputStream("files/outpost.save"))) {
            out.writeInt(roster.size());          // how many records follow
            for (CrewMember member : roster) {
                writeCrewMember(out, member);
            }
            System.out.println("Saved outpost.save with " + roster.size() + " crew members.");
        } catch (IOException e) {
            System.out.println("Could not write the file: " + e.getMessage());
            return;
        }

        try (DataInputStream in = new DataInputStream(new FileInputStream("files/outpost.save"))) {
            int count = in.readInt();             // read the count first
            ArrayList<CrewMember> rebuilt = new ArrayList<CrewMember>();
            for (int i = 0; i < count; i++) {
                rebuilt.add(readCrewMember(in));
            }

            System.out.println();
            System.out.println("Crew report, loaded back from outpost.save:");
            for (CrewMember member : rebuilt) {
                System.out.println("  " + member);
            }
        } catch (IOException e) {
            System.out.println("Could not read the file: " + e.getMessage());
        }
    }
}
