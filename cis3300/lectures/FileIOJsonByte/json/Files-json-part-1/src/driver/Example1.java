package driver; 

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Demo 1 of 4 for org.json. Builds one crew member's data as a
 * JSONObject by hand, the same way you would build any other object, then
 * prints it two ways: the compact default form, and an indented, readable
 * form. Run this first; it does not depend on any file.
 *
 * @author  Dr. Powell
 * @version 1.0
 */
public class Example1 {
	
    public static void main(String[] args) {
        // A JSONObject is a lot like a Map<String, Object>: you put values
        // in under a key, and later get them back out by that same key.
        JSONObject crew = new JSONObject();
        crew.put("name", "Priya Nair");
        crew.put("role", "Engineer");
        crew.put("health", 92);
        crew.put("morale", 88.5);
        crew.put("onDuty", true);

        // A JSONArray holds an ordered list of values, here a list of
        // strings. Nesting a JSONArray inside a JSONObject is how JSON
        // represents "this field is actually a list."
        JSONArray skills = new JSONArray();
        skills.put("Reactor Maintenance");
        skills.put("Spacewalk Certified");
        crew.put("skills", skills);

        // toString() with no argument gives the compact form, all on one
        // line, which is what actually gets written to a file.
        System.out.println("Compact form:");
        System.out.println(crew.toString());

        // toString(indentFactor) pretty-prints with that many spaces per
        // level of nesting. This is only for reading on screen; the extra
        // whitespace is never required by the JSON format itself.
        System.out.println();
        System.out.println("Indented, for reading:");
        System.out.println(crew.toString(2));
    }
}
