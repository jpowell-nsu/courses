package driver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * In-class problem for the byte file day: Save and Resume.
 *
 * Today's session roster is already built below in main. As a class, we
 * are going to give Starfall Outpost the ability to save the current
 * session to outpost.save and resume it later, using DataOutputStream and
 * DataInputStream, the same way today's Demo 4 saved and loaded a whole
 * roster.
 *
 *   1. Fill in saveRoster: write how many crew members there are, then
 *      each crew member's fields, in a fixed order.
 *   2. Fill in loadRoster: read that same information back, in that same
 *      fixed order, and rebuild the roster.
 *   3. main saves the roster, then loads it back into a new list, so we
 *      can see the resume actually works.
 *
 * If we do not finish in class, finish the TODOs on your own before next
 * class for practice. The finished program should look a lot like today's
 * Demo 4, just organized as its own save and load methods.
 *
 * @author  (finish this together in class)
 * @version 1.0
 */
public class SaveAndResume {

    /**
     * Saves a roster of crew members to filename as binary data.
     *
     * @param roster   the crew roster to save
     * @param filename the file to write
     * @throws IOException if the file cannot be written
     */
    private static void saveRoster(ArrayList<CrewMember> roster, String filename) throws IOException {
        // TODO 1: open a DataOutputStream on filename, using
        // try-with-resources so it closes automatically.

        // TODO 2: write roster.size() first, so loadRoster knows how many
        // records are coming.

        // TODO 3: loop over roster. For each CrewMember, write name,
        // role, health, morale, and onDuty, in that order. Then write the
        // number of skills, followed by each skill, the same pattern
        // Demo 4 used.
    }

    /**
     * Loads a roster of crew members back from filename.
     *
     * @param filename the file to read
     * @return the rebuilt roster
     * @throws IOException if the file cannot be read
     */
    private static ArrayList<CrewMember> loadRoster(String filename) throws IOException {
        ArrayList<CrewMember> roster = new ArrayList<CrewMember>();

        // TODO 4: open a DataInputStream on filename, using
        // try-with-resources.

        // TODO 5: read the count written in TODO 2, then loop that many
        // times. Each time, read name, role, health, morale, and onDuty
        // in the exact order saveRoster wrote them, build a CrewMember,
        // then read the skill count and that many skills, adding each one
        // with addSkill. Add the finished CrewMember to roster.

        return roster;
    }

    public static void main(String[] args) {
        ArrayList<CrewMember> sessionRoster = new ArrayList<CrewMember>();

        CrewMember omar = new CrewMember("Omar Reyes", "Pilot", 41, 52.0, true);
        omar.addSkill("Orbital Navigation");
        sessionRoster.add(omar);

        CrewMember kira = new CrewMember("Kira Voss", "Geologist", 35, 70.0, false);
        kira.addSkill("Mineral Analysis");
        kira.addSkill("Drone Operation");
        sessionRoster.add(kira);

        try {
            saveRoster(sessionRoster, "files/outpost.save");
            System.out.println("Session saved.");

            ArrayList<CrewMember> resumed = loadRoster("files/outpost.save");
            System.out.println();
            System.out.println("Session resumed, " + resumed.size() + " crew members loaded:");
            for (CrewMember member : resumed) {
                System.out.println("  " + member);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
