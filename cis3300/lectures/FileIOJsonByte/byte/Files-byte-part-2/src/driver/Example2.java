package driver;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Demo 2 of 4 for byte files. Reads save.dat back in and rebuilds a
 * CrewMember object from it. The readX calls below must appear in the
 * exact same order as the writeX calls that created the file. Nothing in
 * the file tells us that order; the program has to already know it. Run
 * ByteDemo1_WritePrimitives first, so save.dat exists.
 *
 * @author  Dr. Powell
 * @version 1.0
 */
public class Example2 {
	
    public static void main(String[] args) {
        try (DataInputStream in = new DataInputStream(new FileInputStream("files/save.dat"))) {
            String name = in.readUTF();      // must match the writeUTF order
            String role = in.readUTF();
            int health = in.readInt();
            double morale = in.readDouble();
            boolean onDuty = in.readBoolean();

            CrewMember member = new CrewMember(name, role, health, morale, onDuty);

            int skillCount = in.readInt();   // read the count first
            for (int i = 0; i < skillCount; i++) {
                member.addSkill(in.readUTF());
            }

            System.out.println("Rebuilt from save.dat:");
            System.out.println(member);
        } catch (IOException e) {
            System.out.println("Could not read the file: " + e.getMessage());
        }
    }
}
