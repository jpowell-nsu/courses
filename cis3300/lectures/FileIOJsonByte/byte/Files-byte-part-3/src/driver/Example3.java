package driver;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Demo 3 of 4 for byte files. Looks directly at the raw bytes in
 * save.dat, the same file ByteDemo1 wrote. Compares its size against
 * crew.json from the JSON day, and prints its first several bytes as raw
 * numbers, to show why opening a .dat file in a text editor shows garbled
 * or unreadable characters instead of readable text.
 *
 * @author  Dr. Powell
 * @version 1.0
 */
public class Example3 {
	
    public static void main(String[] args) {
        File dataFile = new File("files/save.dat");
        System.out.println("save.dat is " + dataFile.length() + " bytes.");

        File jsonFile = new File("crew.json");
        if (jsonFile.exists()) {
            System.out.println("crew.json is " + jsonFile.length()
                    + " bytes, for comparison (same crew member, JSON day).");
        }

        // A byte in Java is signed, ranging from -128 to 127. Masking
        // with 0xFF gives the unsigned value, 0 to 255, which is how the
        // byte is usually described when talking about file formats.
        System.out.println();
        System.out.println("First 24 raw bytes of save.dat, as unsigned numbers:");
        try (FileInputStream in = new FileInputStream(dataFile)) {
        	int b = 0;
            for (int i = 0; i < 24 && b != -1; i++) {
                b = in.read();
                System.out.print((b & 0xFF) + " ");
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println("Could not read the file: " + e.getMessage());
        }

        System.out.println();
        System.out.println("There are no quotes, no field names, and no readable");
        System.out.println("structure in that output. A text editor tries to treat");
        System.out.println("every byte as a character, so most of this shows up as");
        System.out.println("garbage on screen. The bytes are only meaningful to a");
        System.out.println("program that reads them back in the same order they");
        System.out.println("were written.");
    }
}
