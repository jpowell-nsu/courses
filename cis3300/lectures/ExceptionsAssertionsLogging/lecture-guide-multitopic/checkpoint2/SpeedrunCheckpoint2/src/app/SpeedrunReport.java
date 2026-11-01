package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import speedrun.Run;
import speedrun.RunParseException;
import speedrun.RunParser;

/**
 * Reads the submitted runs, keeps the good ones, and records the bad lines.
 * Checkpoint 2: one bad line no longer stops the program. Each bad line is
 * written to files/rejected.txt and logged as a warning.
 * The Speedrun Leaderboard: a running example for CIS 3300.
 *
 * @author  Dr. Powell
 * @version 1.2
 */
public class SpeedrunReport {
    private static final Logger LOG = Logger.getLogger(SpeedrunReport.class.getName());

    public static void main(String[] args) {
        // The one-line log format from the Logging notes, set here in code so it
        // travels with the project instead of living in a run configuration.
        System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s: %5$s%6$s%n");

        String fileName = "files/runs.csv";
        try {
            LOG.info("Loading " + fileName);
            List<String> lines = Files.readAllLines(Path.of(fileName));
            List<Run> runs = new ArrayList<>();
            List<RunParseException> rejected = new ArrayList<>();

            // Line 0 is the header. Element i of the list is line i + 1 of the file.
            for (int i = 1; i < lines.size(); i++) {
                try {
                    runs.add(RunParser.parse(i + 1, lines.get(i)));
                } catch (RunParseException e) {
                    rejected.add(e);
                    LOG.warning("Rejected " + e.getMessage());
                }
            }

            writeRejected(fileName, rejected, "files/rejected.txt");
            LOG.info("Loaded " + runs.size() + " runs and rejected "
                    + rejected.size() + " lines");
        } catch (NoSuchFileException e) {
            System.out.println("Could not find the file " + e.getFile());
        } catch (IOException e) {
            System.out.println("Could not read the file " + fileName + ": "
                    + e.getMessage());
        }
    }

    private static void writeRejected(String source, List<RunParseException> rejected,
            String outFile) throws IOException {
        try (PrintWriter out = new PrintWriter(outFile)) {
            out.println("Rejected lines from " + source + ": " + rejected.size());
            for (RunParseException e : rejected) {
                out.println();
                out.println(e.getMessage());
                out.println("    " + e.getLine());
            }
        }
    }
}
