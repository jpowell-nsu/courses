package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
 * Reads the submitted runs, keeps the good ones, records the bad lines, and
 * writes the leaderboard report. Checkpoint 6, the finished program: each
 * board shows one personal best per player, the world records are listed
 * newest first, and the report is written to files/leaderboard_report.txt.
 * The Speedrun Leaderboard: a running example for CIS 3300.
 *
 * @author  Dr. Powell
 * @version 1.6
 */
public class SpeedrunReport {
    private static final Logger LOG = Logger.getLogger(SpeedrunReport.class.getName());

    /** Newest date first; runs on the same date fall back on their natural order. */
    private static final Comparator<Run> NEWEST_FIRST =
            Comparator.comparing(Run::date).reversed()
                    .thenComparing(Comparator.naturalOrder());

    public static void main(String[] args) {
        // The one-line log format from the Logging notes, set here in code so it
        // travels with the project instead of living in a run configuration.
        System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s: %5$s%6$s%n");

        String fileName = "files/runs.csv";
        String reportName = "files/leaderboard_report.txt";
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

            writeReport(reportName, runs);
            LOG.info("Wrote " + reportName);
        } catch (NoSuchFileException e) {
            System.out.println("Could not find the file " + e.getFile());
        } catch (IOException e) {
            System.out.println("Could not read the file " + fileName + ": "
                    + e.getMessage());
        }
    }

    private static void writeReport(String outFile, List<Run> runs) throws IOException {
        Map<Board, List<Run>> runsByBoard = runs.stream()
                .collect(Collectors.groupingBy(Run::board, TreeMap::new,
                        Collectors.toList()));
        try (PrintWriter out = new PrintWriter(outFile)) {
            writeSummary(out, runs);
            writeBoards(out, runsByBoard);
            writeRecords(out, runsByBoard);
        }
    }

    private static void writeSummary(PrintWriter out, List<Run> runs) {
        long verifiedCount = runs.stream().filter(Run::verified).count();
        out.println("Speedrun Leaderboard Report");
        out.println(runs.size() + " runs, " + verifiedCount + " verified");

        Set<String> players = runs.stream()
                .map(Run::player)
                .collect(Collectors.toCollection(TreeSet::new));
        out.println(players.size() + " players:");
        out.println(players);

        Map<String, Long> runsPerGame = runs.stream()
                .collect(Collectors.groupingBy(Run::game, TreeMap::new,
                        Collectors.counting()));
        out.println();
        out.println("Runs per game");
        runsPerGame.forEach((game, count) ->
                out.printf("  %-38s %2d%n", game, count));
    }

    private static void writeBoards(PrintWriter out, Map<Board, List<Run>> runsByBoard) {
        for (Map.Entry<Board, List<Run>> entry : runsByBoard.entrySet()) {
            Leaderboard<Run> top = new Leaderboard<>(3);
            personalBests(entry.getValue()).forEach(top::add);
            out.println();
            printBoard(out, entry.getKey() + ": top 3 personal bests", top);
        }
    }

    /**
     * @param runs the runs on one board
     * @return each player's fastest verified run on that board
     */
    private static Collection<Run> personalBests(List<Run> runs) {
        return runs.stream()
                .filter(Run::verified)
                .collect(Collectors.toMap(Run::player, run -> run,
                        (first, second) -> first.compareTo(second) <= 0 ? first : second))
                .values();
    }

    private static void writeRecords(PrintWriter out, Map<Board, List<Run>> runsByBoard) {
        List<Run> records = new ArrayList<>();
        for (List<Run> boardRuns : runsByBoard.values()) {
            boardRuns.stream()
                    .filter(Run::verified)
                    .min(Comparator.naturalOrder())
                    .ifPresent(records::add);
        }
        records.sort(NEWEST_FIRST);

        out.println();
        out.println("World Records, newest first");
        for (Run run : records) {
            out.printf("  %s  %-43s %-10s %10s%n", run.date(), run.board(),
                    run.player(), RunTime.format(run.time()));
        }
    }

    private static void printBoard(PrintWriter out, String title, Leaderboard<Run> board) {
        out.println(title);
        List<Run> entries = board.getEntries();
        for (int i = 0; i < entries.size(); i++) {
            Run run = entries.get(i);
            out.printf("%2d. %-10s %10s  %s%n", i + 1, run.player(),
                    RunTime.format(run.time()), run.date());
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
