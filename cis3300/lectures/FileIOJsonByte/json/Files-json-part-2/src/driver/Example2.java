package driver;

import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Demo 2 of 4 for org.json. Builds the same kind of JSONObject as
 * Demo 1, then writes it out to crew.json. Run this before Demo 3, since
 * Demo 3 reads the file this program creates.
 *
 * @author  Dr. Powell
 * @version 1.0
 */
public class Example2 {
	
    public static void main(String[] args) {
        JSONObject crew = new JSONObject();
        crew.put("name", "Priya Nair");
        crew.put("role", "Engineer");
        crew.put("health", 92);
        crew.put("morale", 88.5);
        crew.put("onDuty", true);

        JSONArray skills = new JSONArray();
        skills.put("Reactor Maintenance");
        skills.put("Spacewalk Certified");
        crew.put("skills", skills);

        // A JSONObject does not write itself to disk. We still write text
        // to a file the same way we did earlier in the semester, we are
        // just writing the JSON text instead of our own formatted lines.
        try (FileWriter writer = new FileWriter("files/crew.json")) {
        	writer.write(crew.toString());
            //writer.write(crew.toString(2));
            System.out.println("Wrote crew.json");
        } catch (IOException e) {
            System.out.println("Could not write the file: " + e.getMessage());
        }
    }
}
