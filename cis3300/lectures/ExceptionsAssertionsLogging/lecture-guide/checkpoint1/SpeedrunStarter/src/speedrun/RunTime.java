package speedrun;

import java.time.Duration;

/**
 * Turns run times written the way speedrunners write them, such as 27:41.6
 * or 1:42:07.3, into Duration objects, and back again. PROVIDED: use it as
 * it is.
 * The Speedrun Leaderboard: a running example for CIS 3300.
 *
 * @author  Dr. Powell
 * @version 1.0
 */
public final class RunTime {

    private RunTime() { }

    /**
     * Reads a time written as M:SS.s or H:MM:SS.s. A leading minus sign is
     * allowed here, so that the Run record, not this method, decides that a
     * negative time is wrong.
     *
     * @param text the time as written in the file
     * @return the time as a Duration
     * @throws NumberFormatException if a part of the time is not a number
     * @throws IllegalArgumentException if the time has the wrong shape, or
     *     its minutes or seconds are 60 or more
     */
    public static Duration parse(String text) {
        boolean negative = text.startsWith("-");
        String[] parts = (negative ? text.substring(1) : text).split(":");
        if (parts.length < 2 || parts.length > 3) {
            throw new IllegalArgumentException(
                    "time must look like M:SS.s or H:MM:SS.s, but was \"" + text + "\"");
        }
        long hours = parts.length == 3 ? Long.parseLong(parts[0]) : 0;
        long minutes = Long.parseLong(parts[parts.length - 2]);
        double seconds = Double.parseDouble(parts[parts.length - 1]);
        if ((parts.length == 3 && minutes >= 60) || !(seconds >= 0 && seconds < 60)) {
            throw new IllegalArgumentException(
                    "minutes and seconds must be below 60, but time was \"" + text + "\"");
        }
        Duration time = Duration.ofHours(hours).plusMinutes(minutes)
                .plusMillis(Math.round(seconds * 1000));
        return negative ? time.negated() : time;
    }

    /**
     * Writes a time the way speedrunners do, to the tenth of a second:
     * 27:41.6 under an hour, 1:42:07.3 from an hour up.
     *
     * @param time the time to write
     * @return the time as text
     */
    public static String format(Duration time) {
        String sign = time.isNegative() ? "-" : "";
        long tenths = Math.round(time.abs().toMillis() / 100.0);
        long hours = tenths / 36000;
        long minutes = tenths / 600 % 60;
        long seconds = tenths / 10 % 60;
        long tenth = tenths % 10;
        return hours > 0
                ? String.format("%s%d:%02d:%02d.%d", sign, hours, minutes, seconds, tenth)
                : String.format("%s%d:%02d.%d", sign, minutes, seconds, tenth);
    }
}
