package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import speedrun.Board;
import speedrun.Leaderboard;
import speedrun.Run;
import speedrun.RunParseException;
import speedrun.RunParser;
import speedrun.RunTime;

/**
 * Reads the submitted runs, keeps the good ones, and records the bad lines.
 * Checkpoint 5: the report's counting, grouping, and filtering use streams.
 * Leaderboards count only verified runs, and each board's world record is
 * found with a stream and an Optional.
 * The Speedrun Leaderboard: a running example for CIS 3300.
 *
 * @author  Dr. Powell
 * @version 1.5
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

            long verifiedCount = runs.stream().filter(Run::verified).count();
            System.out.println(runs.size() + " runs, " + verifiedCount + " verified");

            Set<String> players = runs.stream()
                    .map(Run::player)
                    .collect(Collectors.toCollection(TreeSet::new));
            System.out.println(players.size() + " players:");
            System.out.println(players);

            Map<String, Long> runsPerGame = runs.stream()
                    .collect(Collectors.groupingBy(Run::game, TreeMap::new,
                            Collectors.counting()));
            System.out.println();
            System.out.println("Runs per game");
            runsPerGame.forEach((game, count) ->
                    System.out.printf("  %-38s %2d%n", game, count));

            Map<Board, List<Run>> runsByBoard = runs.stream()
                    .collect(Collectors.groupingBy(Run::board, TreeMap::new,
                            Collectors.toList()));
            for (Map.Entry<Board, List<Run>> entry : runsByBoard.entrySet()) {
                Leaderboard<Run> top = new Leaderboard<>(3);
                entry.getValue().stream()
                        .filter(Run::verified)
                        .forEach(top::add);
                System.out.println();
                printBoard(entry.getKey() + ": top 3 verified", top);
            }

            System.out.println();
            System.out.println("World Records");
            for (Map.Entry<Board, List<Run>> entry : runsByBoard.entrySet()) {
                Optional<Run> record = entry.getValue().stream()
                        .filter(Run::verified)
                        .min(Comparator.naturalOrder());
                if (record.isPresent()) {
                    Run run = record.get();
                    System.out.printf("  %-43s %-10s %10s  %s%n", entry.getKey(),
                            run.player(), RunTime.format(run.time()), run.date());
                } else {
                    System.out.printf("  %-43s no verified runs%n", entry.getKey());
                }
            }
        } catch (NoSuchFileException e) {
            System.out.println("Could not find the file " + e.getFile());
        } catch (IOException e) {
            System.out.println("Could not read the file " + fileName + ": "
                    + e.getMessage());
        }
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
