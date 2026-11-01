package speedrun;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Objects;

/**
 * One run submitted to the leaderboard. A record is immutable, so the compact
 * constructor checks every value once, and no bad run can ever exist. Runs
 * sort fastest first; equal times go to the earlier date, then by player.
 * The Speedrun Leaderboard: a running example for CIS 3300.
 *
 * @param player the runner's name
 * @param game the game's title
 * @param category the category, such as Any% or 120 Star
 * @param time how long the run took
 * @param date the day the run was done
 * @param verified true if the leaderboard's moderators have checked the run
 * @author  Dr. Powell
 * @version 1.1
 */
public record Run(String player, String game, String category, Duration time,
        LocalDate date, boolean verified) implements Comparable<Run> {

    public Run {
        requireText(player, "player");
        requireText(game, "game");
        requireText(category, "category");
        Objects.requireNonNull(time, "time is required");
        Objects.requireNonNull(date, "date is required");
        if (time.isNegative() || time.isZero()) {
            throw new IllegalArgumentException(
                    "time must be greater than 0, but was " + RunTime.format(time));
        }
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("date " + date + " is in the future");
        }
    }

    /**
     * Orders runs fastest first. When two times are equal, the earlier run
     * comes first, because the first player to reach a time holds it. When
     * the dates are equal too, the runs are ordered by player name.
     */
    @Override
    public int compareTo(Run other) {
        int byTime = time.compareTo(other.time);
        if (byTime != 0) {
            return byTime;
        }
        int byDate = date.compareTo(other.date);
        if (byDate != 0) {
            return byDate;
        }
        return player.compareTo(other.player);
    }

    private static void requireText(String value, String name) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(name + " is required");
        }
    }
}
