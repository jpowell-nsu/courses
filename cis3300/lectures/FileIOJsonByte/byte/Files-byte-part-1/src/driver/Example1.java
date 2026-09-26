package driver;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Demo 1 of 4 for byte files. Writes one crew member's fields to
 * save.dat as raw binary data, using DataOutputStream. Unlike JSON, there
 * are no field names stored in the file, only the values themselves, in
 * the exact order we write them. Run this before Demo 2, since Demo 2
 * reads the file this program creates.
 *
 * @author  Dr. Powell
 * @version 1.0
 */
public class Example1 {
	
    public static void main(String[] args) {
        // try-with-resources closes the stream automatically, even if an
        // exception happens partway through writing.
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream("files/save.dat"))) {
            // Each writeX method writes that type's value as raw bytes:
            // no quotes, no field name, no separator. writeUTF writes a
            // String using a length-prefixed encoding, so it is the one
            // exception, it does store how many bytes follow.
            out.writeUTF("Priya Nair");   // name
            out.writeUTF("Engineer");     // role
            out.writeInt(92);             // health
            out.writeDouble(88.5);        // morale
            out.writeBoolean(true);       // onDuty

            // A list needs its own length written first, so we know how
            // many strings to read back later. This same idea, a count
            // written before the items it describes, comes back in
            // Demo 4 for the whole roster.
            out.writeInt(2);              // number of skills
            out.writeUTF("Reactor Maintenance");
            out.writeUTF("Spacewalk Certified");
            
            // open the file in a text editor to see what happens

            System.out.println("Wrote save.dat");
        } catch (IOException e) {
            System.out.println("Could not write the file: " + e.getMessage());
        }
    }
}
