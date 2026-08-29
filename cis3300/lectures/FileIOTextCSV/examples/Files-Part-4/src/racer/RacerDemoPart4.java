package racer;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.ArrayList;

public class RacerDemoPart4 {
    public static void main(String[] args) throws Exception {
        ArrayList<Racer> racers = new ArrayList<Racer>();

        // CSVReader hands back one line at a time as a String array,
        // already split on the commas (even commas inside quotes).
        CSVReader reader = new CSVReader(new FileReader("files/racers.csv"));
        reader.readNext(); // skip the header row

        String[] line;
        while ((line = reader.readNext()) != null) {
            String name = line[0];
            String vehicle = line[1];
            double topSpeed = Double.parseDouble(line[2]);
            racers.add(new Racer(name, vehicle, topSpeed));
        }
        reader.close();

        // racers is an ArrayList<Racer>, same as Part 1; print it the same way.
        for (int i = 0; i < racers.size(); i++) {
            System.out.println(racers.get(i).getName());
        }
    }
}
