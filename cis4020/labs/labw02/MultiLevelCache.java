import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Simulates a chain of cache levels and main memory, using a single
 * recursive function to decide how each memory access in a trace is
 * served.
 *
 * Assignment: Lab W02: A Recursive Multi-Level Cache Simulator
 * @version 1.0
 * @author Your Name
 *
 * The input parsing, output formatting, and summary statistics are
 * already written below. Your job is to implement accessLevel(blockID,
 * level), the single recursive function that decides how each memory
 * access is served. For a typical solution, accessLevel is the only
 * method you should need to write or change. Do not rewrite it as a
 * loop. The point of the assignment is the recursive structure itself.
 */
public class MultiLevelCache {

    // capacity[i] and hitTime[i] describe cache level i (0 is the fastest,
    // fed by the input, one entry per cache level).
    static int[] capacity;
    static int[] hitTime;
    static int mainMemoryTime;
    static int numLevels;

    // cacheContents.get(i) is the list of block IDs currently stored at
    // level i. All levels start empty, so this begins as a list of N
    // empty lists.
    static List<List<Integer>> cacheContents;

    // Running totals used for the summary printed after the trace.
    // hitsPerLevel[i] counts how many accesses were served by level i.
    static int[] hitsPerLevel;
    static int mainMemoryHits;

    // Set by accessLevel() on every call so main() knows, after the
    // call returns, which level actually served the request. This is
    // simpler than returning two pieces of information at once.
    static String lastServedBy;

    public static void main(String[] args) {
        // Reads from a file named input.txt in the working directory,
        // rather than from the console, so you can run this from an IDE
        // without setting up console input.
        Scanner in;
        try {
            in = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Could not find input.txt. Make sure it is in your project's working directory.");
            return;
        }

        // Line 1: N, the number of cache levels.
        numLevels = Integer.parseInt(in.nextLine().trim());
        capacity = new int[numLevels];
        hitTime = new int[numLevels];
        cacheContents = new ArrayList<>();

        // Next N lines: one "capacity hitTime" pair per level, in order
        // from level 0 (fastest) to level N - 1. Each level also gets an
        // empty list to hold whatever blocks end up cached there.
        for (int i = 0; i < numLevels; i++) {
            String[] parts = in.nextLine().trim().split("\\s+");
            capacity[i] = Integer.parseInt(parts[0]);
            hitTime[i] = Integer.parseInt(parts[1]);
            cacheContents.add(new ArrayList<>());
        }

        // Next line: main memory access time.
        mainMemoryTime = Integer.parseInt(in.nextLine().trim());
        // Next line: M, the number of accesses in the trace.
        int m = Integer.parseInt(in.nextLine().trim());
        // Next line: the M block IDs themselves, space separated.
        String[] traceParts = in.nextLine().trim().split("\\s+");

        hitsPerLevel = new int[numLevels];
        mainMemoryHits = 0;
        long totalCycles = 0;

        // Replay the trace one block at a time. Each call to
        // accessLevel() starts the search at level 0 and walks down the
        // chain of levels itself, recursively, until something answers
        // it. The caches persist across iterations of this loop, which
        // is what lets an earlier miss turn into a later hit.
        for (int i = 0; i < m; i++) {
            int blockID = Integer.parseInt(traceParts[i]);
            int cost = accessLevel(blockID, 0);
            totalCycles += cost;
            System.out.println("Access " + (i + 1) + ": block " + blockID
                    + " -> served by " + lastServedBy + ", cost " + cost + " cycles");
        }

        // Print the required summary: hits per level, main memory hits,
        // total cycles spent, and the average cost per access.
        System.out.println();
        System.out.println("Summary:");
        for (int i = 0; i < numLevels; i++) {
            System.out.println("  L" + (i + 1) + " hits: " + hitsPerLevel[i]);
        }
        System.out.println("  Main Memory hits: " + mainMemoryHits);
        System.out.println("  Total cycles: " + totalCycles);
        System.out.printf("  Average cycles per access: %.2f%n", (double) totalCycles / m);
    }

    /**
     * Returns the cost, in cycles, of servicing blockID starting the
     * search at the given level (0 is the fastest cache level).
     *
     * Rules to implement:
     *   1. Base case: if level == numLevels, we have fallen off the last
     *      cache level. The access is served by main memory, which always
     *      succeeds. Update mainMemoryHits and lastServedBy, then return
     *      mainMemoryTime.
     *   2. If cacheContents.get(level) already contains blockID, this
     *      level serves the request directly. Update hitsPerLevel and
     *      lastServedBy, then return hitTime[level].
     *   3. Otherwise, this level misses. Recurse to level + 1 to find out
     *      how the request gets served further down, then add this
     *      level's hitTime to that cost. After the recursive call
     *      returns, if this level still has room (compare its current
     *      size to capacity[level]), insert blockID here. This is the
     *      "fill on the way back up" step, and it only makes sense after
     *      the recursive call has returned. Return the combined cost.
     *
     * Do not implement eviction. If a level is full, simply skip the
     * insertion and move on.
     */
    static int accessLevel(int blockID, int level) {
        // TODO: implement the three cases described above.
        return -1;
    }
}
