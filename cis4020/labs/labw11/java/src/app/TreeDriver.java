package app;

import tree.TwoThreeTree;

/**
 * Runs the checkpoint tests and the example sequences for Lab W11.
 *
 * This file is complete. You may add your own tests at the bottom of main.
 */
public class TreeDriver {

    public static void main(String[] args) {
    	
        System.out.println("===== Checkpoint tests =====");
        runCheckpoint("Checkpoint 1", new int[] {50, 30});
        runCheckpoint("Checkpoint 2", new int[] {50, 30, 70});
        runCheckpoint("Checkpoint 3", new int[] {50, 30, 70, 10, 20});
        runCheckpoint("Checkpoint 4",
                new int[] {42, 36, 16, 14, 32, 31, 40, 8, 21, 34, 20, 22, 50, 15});

        System.out.println();
        System.out.println("===== Example sequences =====");
        runSequence("Sequence A",
                new int[] {11, 39, 61, 96, 74, 9, 24, 67, 69, 64, 52, 65, 82, 60});
        runSequence("Sequence B",
                new int[] {46, 80, 88, 13, 35, 91, 23, 6, 57, 56, 90, 53, 99, 81});
        runSequence("Sequence C",
                new int[] {98, 7, 75, 33, 45, 19, 38, 4, 62, 66, 1, 76, 86, 59});

        // ---- Add your own tests below this line, if you like ----
    }

    /** Inserts every value, then prints the final tree and its inorder traversal. */
    private static void runCheckpoint(String name, int[] values) {
        System.out.println();
        System.out.println(name + ": insert " + join(values));
        try {
            TwoThreeTree tree = new TwoThreeTree();
            for (int v : values) {
                tree.insert(v);
            }
            tree.printTree();
            System.out.println("Inorder: " + tree.inorder());
        } catch (RuntimeException | StackOverflowError e) {
            System.out.println(name + " stopped with an error: " + e);
        }
    }

    /** Inserts the values one at a time, printing the tree after every insertion. */
    private static void runSequence(String name, int[] values) {
        System.out.println();
        System.out.println(name + ": " + join(values));
        try {
            TwoThreeTree tree = new TwoThreeTree();
            for (int v : values) {
                tree.insert(v);
                System.out.println();
                System.out.println("After inserting " + v + ":");
                tree.printTree();
            }
            System.out.println("Inorder: " + tree.inorder());
        } catch (RuntimeException | StackOverflowError e) {
            System.out.println(name + " stopped with an error: " + e);
        }
    }

    private static String join(int[] values) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            if (i > 0) {
                sb.append(' ');
            }
            sb.append(values[i]);
        }
        return sb.toString();
    }
}
