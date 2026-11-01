package speedrun;

/**
 * One leaderboard: a game and one of its categories, such as Celeste, Any%.
 * Boards sort by game, then by category, so a TreeMap keeps them in that
 * order.
 * The Speedrun Leaderboard: a running example for CIS 3300.
 *
 * @param game the game's title
 * @param category the category, such as Any% or 120 Star
 * @author  Dr. Powell
 * @version 1.0
 */
public record Board(String game, String category) implements Comparable<Board> {

    @Override
    public int compareTo(Board other) {
        int byGame = game.compareTo(other.game);
        if (byGame != 0) {
            return byGame;
        }
        return category.compareTo(other.category);
    }

    @Override
    public String toString() {
        return game + ", " + category;
    }
}
