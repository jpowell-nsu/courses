/**
 * Thrown when one line of a staff file cannot be turned into an employee.
 * It is checked, because a bad line in a file is something the caller can
 * expect and recover from, for example by skipping the line.
 */
public class BadRecordException extends Exception {
    private static final long serialVersionUID = 1L;

    private final int lineNumber;

    public BadRecordException(int lineNumber, String message) {
        super("line " + lineNumber + ": " + message);
        this.lineNumber = lineNumber;
    }

    public BadRecordException(int lineNumber, String message, Throwable cause) {
        super("line " + lineNumber + ": " + message, cause);
        this.lineNumber = lineNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}
