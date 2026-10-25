package redblack;

/**
 * Runs the insertion and deletion examples from the notes, printing the tree
 * after every operation and checking the red-black rules each time.
 */
public class RedBlackDriver {

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();

        System.out.println("===== Insertion example =====");
        for (int v : new int[] {60, 55, 85, 25, 45, 15, 20, 30}) {
            tree.insert(v);
            System.out.println();
            System.out.println("After inserting " + v + ":");
            tree.printTree();
            tree.check();
        }

        System.out.println();
        System.out.println("===== Deletion example =====");
        for (int v : new int[] {85, 45, 15, 60, 20}) {
            tree.delete(v);
            System.out.println();
            System.out.println("After deleting " + v + ":");
            tree.printTree();
            tree.check();
        }
    }
}
