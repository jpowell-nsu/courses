package driver;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * In-class problem for the org.json day: the Sector Scan Report.
 *
 * Starfall Outpost wants a daily summary of crew status. roster.json holds
 * an array of crew member records, in the same shape used in today's demo.
 * As a class, we are going to:
 *
 *   1. Read roster.json and rebuild it as an ArrayList of CrewMember.
 *   2. Compute three things from that roster:
 *        - the average morale, across every crew member
 *        - how many crew members are currently on duty
 *        - the names of any crew member whose health is below 50
 *          (they need medical attention)
 *   3. Build a JSONObject named report holding those three results.
 *   4. Write that report out to sectorReport.json, pretty-printed.
 *
 * Some of this is already written below. The TODOs are what we build
 * together in class. If we do not finish, finish the TODOs on your own
 * before next class for practice. The finished program should look a lot
 * like today's roster demo, just computing a report instead of printing
 * one.
 *
 * @author  (finish this together in class)
 * @version 1.0
 */
public class SectorScanReport {

    /**
     * Reads roster.json and rebuilds it as an ArrayList of CrewMember.
     * This mirrors the fromJson helper from today's Demo 4.
     *
     * @param filename the JSON file to read
     * @return the roster as a list of CrewMember objects
     * @throws IOException if the file cannot be read
     */
    private static ArrayList<CrewMember> loadRoster(String filename) throws IOException {
        ArrayList<CrewMember> roster = new ArrayList<CrewMember>();

        // TODO 1: read the whole file into a String, then build a
        // JSONArray from that String.

        // TODO 2: loop over the JSONArray by index. For each JSONObject,
        // pull out name, role, health, morale, and onDuty, build a
        // CrewMember, then loop its nested "skills" JSONArray and add
        // each skill with addSkill. Add the finished CrewMember to roster.

        return roster;
    }

    /**
     * Builds the sector scan report as a JSONObject from a roster.
     *
     * @param roster the crew roster to summarize
     * @return a JSONObject holding the report fields
     */
    private static JSONObject buildReport(ArrayList<CrewMember> roster) {
        JSONObject report = new JSONObject();

        // TODO 3: compute the average morale across every crew member in
        // roster, and put it in report under the key "averageMorale".

        // TODO 4: count how many crew members are on duty, and put that
        // count in report under the key "onDutyCount".

        // TODO 5: build a JSONArray of the names of any crew member with
        // health below 50, and put it in report under the key
        // "needsMedicalAttention".

        report.put("crewCount", roster.size());
        return report;
    }

    public static void main(String[] args) {
        try {
            ArrayList<CrewMember> roster = loadRoster("files/roster.json");
            System.out.println("Loaded " + roster.size() + " crew members.");

            JSONObject report = buildReport(roster);

            // TODO 6: write report out to sectorReport.json, pretty-printed
            // with an indent factor of 2, the same way Demo 2 wrote
            // crew.json.

            System.out.println("Wrote sectorReport.json");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
