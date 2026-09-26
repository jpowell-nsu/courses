package driver;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Demo 4 of 4 for org.json. Builds a small roster of several
 * CrewMember objects, writes the whole roster to roster.json as a single
 * JSONArray, then reads it back and prints a short report. This is the
 * pattern the in-class problem builds on: a JSONArray of JSONObjects,
 * each with a nested JSONArray of its own.
 *
 * @author  Dr. Powell
 * @version 1.0
 */
public class Example4 {

    /** Converts one CrewMember into a JSONObject. */
    private static JSONObject toJson(CrewMember member) {
        JSONObject obj = new JSONObject();
        obj.put("name", member.getName());
        obj.put("role", member.getRole());
        obj.put("health", member.getHealth());
        obj.put("morale", member.getMorale());
        obj.put("onDuty", member.isOnDuty());
        obj.put("skills", new JSONArray(member.getSkills()));
        return obj;
    }

    /** Converts one JSONObject back into a CrewMember. */
    private static CrewMember fromJson(JSONObject obj) {
        CrewMember member = new CrewMember(
                obj.getString("name"),
                obj.getString("role"),
                obj.getInt("health"),
                obj.getDouble("morale"),
                obj.getBoolean("onDuty"));
        JSONArray skills = obj.getJSONArray("skills");
        for (int i = 0; i < skills.length(); i++) {
            member.addSkill(skills.getString(i));
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

        // Build one JSONArray holding one JSONObject per crew member.
        JSONArray rosterJson = new JSONArray();
        for (CrewMember member : roster) {
            rosterJson.put(toJson(member));
        }

        try (FileWriter writer = new FileWriter("files/roster.json")) {
            writer.write(rosterJson.toString(2));
            System.out.println("Wrote roster.json with " + roster.size() + " crew members.");
        } catch (IOException e) {
            System.out.println("Could not write the file: " + e.getMessage());
            return;
        }

        // Read the array back and rebuild the ArrayList, to prove the
        // round trip works.
        try {
            String text = Files.readString(Path.of("files/roster.json"));
            JSONArray readBack = new JSONArray(text);

            ArrayList<CrewMember> rebuilt = new ArrayList<CrewMember>();
            for (int i = 0; i < readBack.length(); i++) {
                rebuilt.add(fromJson(readBack.getJSONObject(i)));
            }

            System.out.println();
            System.out.println("Crew report, read back from roster.json:");
            for (CrewMember member : rebuilt) {
                System.out.println("  " + member);
            }
        } catch (IOException e) {
            System.out.println("Could not read the file: " + e.getMessage());
        }
    }
}
