package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Logger;
import speedrun.Board;
import speedrun.Leaderboard;
import speedrun.Run;
import speedrun.RunParseException;
import speedrun.RunParser;
import speedrun.RunTime;

/**
 * Reads the submitted runs, keeps the good ones, and records the bad lines.
 * Checkpoint 4: runs are grouped by board in a TreeMap, the players are kept
 * in a TreeSet, and every board gets its own Leaderboard.
 * The Speedrun Leaderboard: a running example for CIS 3300.
 *
 * @author  Dr. Powell
 * @version 1.4
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

            Set<String> players = new TreeSet<>();
            for (Run run : runs) {
                players.add(run.player());
            }
            System.out.println(players.size() + " players:");
            System.out.println(players);

            Map<Board, List<Run>> runsByBoard = groupByBoard(runs);
            for (Map.Entry<Board, List<Run>> entry : runsByBoard.entrySet()) {
                Leaderboard<Run> top = new Leaderboard<>(3);
                for (Run run : entry.getValue()) {
                    top.add(run);
                }
                System.out.println();
                printBoard(entry.getKey() + ": " + entry.getValue().size() + " runs, top 3", top);
            }
        } catch (NoSuchFileException e) {
            System.out.println("Could not find the file " + e.getFile());
        } catch (IOException e) {
            System.out.println("Could not read the file " + fileName + ": "
                    + e.getMessage());
        }
    }

    private static Map<Board, List<Run>> groupByBoard(List<Run> runs) {
        Map<Board, List<Run>> result = new TreeMap<>();
        for (Run run : runs) {
            List<Run> list = result.get(run.board());
            if (list == null) {
                list = new ArrayList<>();
                result.put(run.board(), list);
            }
            list.add(run);
        }
        return result;
    }

    private static void printBoard(String title, Leaderboard<Run> board) {
        System.out.println(title);
        List<Run> entries = board.getEntries();
        for (int i = 0; i < entries.size(); i++) {
            Run run = entries.get(i);
            System.out.printf("%2d. %-10s %10s  %s%s%n", i + 1, run.player(),
                    RunTime.format(run.time()), run.date(),
                    run.verified() ? "" : "  (unverified)");
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
