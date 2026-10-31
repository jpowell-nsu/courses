package app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import speedrun.Run;
import speedrun.RunTime;

/**
 * Reads the submitted runs and prints each one. Checkpoint 1: a missing file
 * gives one clear message, and each line becomes a Run record.
 * The Speedrun Leaderboard: a running example for CIS 3300.
 *
 * @author  Dr. Powell
 * @version 1.1
 */
public class SpeedrunReport {

    public static void main(String[] args) {
        String fileName = "files/runs_clean.csv";
        try {
            List<String> lines = Files.readAllLines(Path.of(fileName));

            // Line 0 is the header, so the runs start at line 1.
            for (int i = 1; i < lines.size(); i++) {
                String[] fields = lines.get(i).split(",");
                Run run = new Run(fields[0], fields[1], fields[2],
                        RunTime.parse(fields[3]), LocalDate.parse(fields[4]),
                        fields[5].equals("yes"));

                System.out.printf("%-10s %-36s %-8s %10s  %s  %s%n", run.player(),
                        run.game(), run.category(), RunTime.format(run.time()),
                        run.date(), run.verified() ? "verified" : "unverified");
            }
        } catch (NoSuchFileException e) {
            System.out.println("Could not find the file " + e.getFile());
        } catch (IOException e) {
            System.out.println("Could not read the file " + fileName + ": " + e.getMessage());
        }
    }
}
