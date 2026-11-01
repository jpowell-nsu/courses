package speedrun;

/**
 * Thrown when one line of a runs file cannot become a Run. It is checked,
 * because a bad line comes from the file, not from a mistake in the code,
 * and the caller can always recover by skipping the line.
 * The Speedrun Leaderboard: a running example for CIS 3300.
 *
 * @author  Dr. Powell
 * @version 1.0
 */
public class RunParseException extends Exception {
    private static final long serialVersionUID = 1L;

    private final int lineNumber;
    private final String line;

    /**
     * @param lineNumber the line's number in the file, counting the header as 1
     * @param line the text of the bad line
     * @param message what is wrong with the line
     */
    public RunParseException(int lineNumber, String line, String message) {
        super("line " + lineNumber + ": " + message);
        this.lineNumber = lineNumber;
        this.line = line;
    }

    /**
     * @param lineNumber the line's number in the file, counting the header as 1
     * @param line the text of the bad line
     * @param message what is wrong with the line
     * @param cause the exception that revealed the problem
     */
    public RunParseException(int lineNumber, String line, String message,
            Throwable cause) {
        super("line " + lineNumber + ": " + message, cause);
        this.lineNumber = lineNumber;
        this.line = line;
    }

    /** @return the line's number in the file */
    public int getLineNumber() {
        return lineNumber;
    }

    /** @return the text of the bad line */
    public String getLine() {
        return line;
    }
}
