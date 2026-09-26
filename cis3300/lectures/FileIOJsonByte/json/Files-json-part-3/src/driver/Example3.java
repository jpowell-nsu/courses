package driver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Demo 3 of 4 for org.json. Reads crew.json back in and rebuilds a
 * CrewMember object from it. Run JsonDemo2_WriteToFile first, so crew.json
 * exists.
 *
 * @author  Dr. Powell
 * @version 1.0
 */
public class Example3 {
	
    public static void main(String[] args) {
        try {
            // Files.readString reads the whole file into one String. A
            // JSONObject can then be built directly from that String; this
            // is the JSON equivalent of reading a whole CSV line before
            // splitting it.
            String text = Files.readString(Path.of("files/crew.json"));
            JSONObject crew = new JSONObject(text);

            // Named getters pull typed values back out by key. Using the
            // wrong getter, for example getInt on a field that is really
            // a String, throws a JSONException.
            String name = crew.getString("name");
            String role = crew.getString("role");
            int health = crew.getInt("health");
            double morale = crew.getDouble("morale");
            boolean onDuty = crew.getBoolean("onDuty");

            CrewMember member = new CrewMember(name, role, health, morale, onDuty);

            // getJSONArray gets the nested list back out. Looping over it
            // by index, the same way we loop over any array, adds each
            // skill to the CrewMember one at a time.
            JSONArray skills = crew.getJSONArray("skills");
            for (int i = 0; i < skills.length(); i++) {
                member.addSkill(skills.getString(i));
            }

            System.out.println("Rebuilt from crew.json:");
            System.out.println(member);
        } catch (IOException e) {
            System.out.println("Could not read the file: " + e.getMessage());
        }
    }
}
