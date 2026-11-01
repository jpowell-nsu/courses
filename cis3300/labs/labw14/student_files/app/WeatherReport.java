package app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

/**
 * STARTER PROGRAM, PROVIDED TO STUDENTS. Reads the readings file, splits each
 * line on commas, and prints each reading. It works on a clean file, and it
 * crashes on a bad line or a missing file. You will change it as you work
 * through the tasks.
 * Lab W14: The Weather Station Network.
 *
 * @author  Dr. Powell
 * @version 1.0
 */
public class WeatherReport {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("files/readings_clean.csv"));

        // Line 0 is the header, so the readings start at line 1.
        for (int i = 1; i < lines.size(); i++) {
            String[] fields = lines.get(i).split(",");
            String station = fields[0];
            LocalDate date = LocalDate.parse(fields[1]);
            double high = Double.parseDouble(fields[2]);
            double low = Double.parseDouble(fields[3]);
            double precipitation = Double.parseDouble(fields[4]);

            System.out.printf("%-4s %s %6.1f %6.1f %5.2f%n", station, date, high, low,
                    precipitation);
        }
    }
}
