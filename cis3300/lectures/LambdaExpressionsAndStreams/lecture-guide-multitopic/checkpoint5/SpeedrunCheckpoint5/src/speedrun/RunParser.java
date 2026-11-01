package speedrun;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Turns one line of a runs file into a Run. Every problem with the line
 * comes out as a RunParseException.
 * The Speedrun Leaderboard: a running example for CIS 3300.
 *
 * @author  Dr. Powell
 * @version 1.0
 */
public final class RunParser {

    private RunParser() { }

    /**
     * @param lineNumber the line's number in the file, counting the header as 1
     * @param line the text of the line
     * @return the run
     * @throws RunParseException if the line is not a good run
     */
    public static Run parse(int lineNumber, String line) throws RunParseException {
        String[] fields = line.split(",", -1);
        if (fields.length != 6) {
            throw new RunParseException(lineNumber, line,
                    "expected 6 fields but found " + fields.length);
        }

        Duration time;
        try {
            time = RunTime.parse(fields[3]);
        } catch (NumberFormatException e) {
            throw new RunParseException(lineNumber, line,
                    "time has a part that is not a number: \"" + fields[3] + "\"", e);
        } catch (IllegalArgumentException e) {
            throw new RunParseException(lineNumber, line, e.getMessage(), e);
        }

        LocalDate date;
        try {
            date = LocalDate.parse(fields[4]);
        } catch (DateTimeParseException e) {
            throw new RunParseException(lineNumber, line,
                    "date is not a real date: \"" + fields[4] + "\"", e);
        }

        boolean verified;
        if (fields[5].equals("yes")) {
            verified = true;
        } else if (fields[5].equals("no")) {
            verified = false;
        } else {
            throw new RunParseException(lineNumber, line,
                    "verified must be yes or no, but was \"" + fields[5] + "\"");
        }

        try {
            return new Run(fields[0], fields[1], fields[2], time, date, verified);
        } catch (IllegalArgumentException e) {
            throw new RunParseException(lineNumber, line, e.getMessage(), e);
        }
    }
}
