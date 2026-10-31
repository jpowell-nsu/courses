package app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import speedrun.RunTime;

/**
 * Reads the submitted runs and prints each one. This is the starter program:
 * it works on a clean file, and it crashes on a bad line or a missing file.
 * The Speedrun Leaderboard: a running example for CIS 3300.
 *
 * @author  Dr. Powell
 * @version 1.0
 */
public class SpeedrunReport {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("files/runs_clean.csv"));

        // Line 0 is the header, so the runs start at line 1.
        for (int i = 1; i < lines.size(); i++) {
            String[] fields = lines.get(i).split(",");
            String player = fields[0];
            String game = fields[1];
            String category = fields[2];
            Duration time = RunTime.parse(fields[3]);
            LocalDate date = LocalDate.parse(fields[4]);
            boolean verified = fields[5].equals("yes");

            System.out.printf("%-10s %-36s %-8s %10s  %s  %s%n",
                    player, game, category, RunTime.format(time), date,
                    verified ? "verified" : "unverified");
        }
    }
}
