import java.io.*;
import java.nio.ByteBuffer;

public class TamperDemo {
    // An ordinary class that checks its data in the constructor.
    static class ScoreClass implements Serializable {
        private static final long serialVersionUID = 1L;
        private final double percent;
        ScoreClass(double percent) {
            if (percent < 0 || percent > 100) {
                throw new IllegalArgumentException("bad percent: " + percent);
            }
            this.percent = percent;
        }
        double percent() { return percent; }
    }

    // A record with the same check in its compact constructor.
    record ScoreRecord(double percent) implements Serializable {
        ScoreRecord {
            if (percent < 0 || percent > 100) {
                throw new IllegalArgumentException("bad percent: " + percent);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Object fromClass = tamper(new ScoreClass(85.0));
        System.out.println("Class came back with " + ((ScoreClass) fromClass).percent());

        try {
            tamper(new ScoreRecord(85.0));
        } catch (InvalidObjectException e) {
            System.out.println("Record rejected: " + e.getMessage());
        }
    }

    // Serialize, overwrite the stored 85.0 with 150.0, then deserialize.
    static Object tamper(Object original) throws Exception {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try (ObjectOutputStream out = new ObjectOutputStream(buffer)) {
            out.writeObject(original);
        }
        byte[] bytes = buffer.toByteArray();
        byte[] good = ByteBuffer.allocate(8).putDouble(85.0).array();
        byte[] evil = ByteBuffer.allocate(8).putDouble(150.0).array();
        for (int i = 0; i + 8 <= bytes.length; i++) {
            if (java.util.Arrays.equals(bytes, i, i + 8, good, 0, 8)) {
                System.arraycopy(evil, 0, bytes, i, 8);
                break;
            }
        }
        try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
            return in.readObject();
        }
    }
}
