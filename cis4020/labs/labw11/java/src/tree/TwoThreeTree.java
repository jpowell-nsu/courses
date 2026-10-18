package tree;

import java.util.ArrayList;

/**
 * A 2-3 tree of integer values. Search, the inorder traversal, and
 * printTree are provided. The three insertion methods, the public insert,
 * the recursive insert, and split, are completed for this lab.
 *
 * Assignment: Lab W11: 2-3 Tree Insertion
 * @version 1.0
 * @author
 *
 */
public class TwoThreeTree {
	
    private TwoThreeNode root;

    /** Creates an empty tree. */
    public TwoThreeTree() {
        root = null;
    }

    // ==================================================================
    // Insertion: YOUR CODE GOES IN THE THREE METHODS BELOW
    // ==================================================================

    /**
     * Inserts value into the tree. Duplicate values are ignored.
     *
     * Handles the two cases that only happen at the top of the tree: an empty
     * tree, and a split that reaches the root.
     */
    public void insert(int value) {
        // TODO (Checkpoints 1 and 2): write the public insert method.
    }

    /**
     * Inserts value into the subtree rooted at node.
     *
     * Returns null when the subtree absorbed the value without splitting
     * node. Returns a SplitResult when node itself had to split, so the
     * caller (one level up) can place the promoted value and the new node.
     */
    private SplitResult insert(TwoThreeNode node, int value) {
        // TODO (Checkpoints 1, 3, and 4): write the recursive insert method.
        return null;
    }

    /**
     * Splits a FULL node that must also take in one more value.
     *
     * rightOfValue is the node that belongs immediately to the right of the
     * new value (null when node is a leaf). When this method returns, node
     * holds the smallest value and the leftmost children, a new node holds
     * the largest value and the rightmost children, and the middle value is
     * handed back in the SplitResult along with that new node.
     */
    private SplitResult split(TwoThreeNode node, int value, TwoThreeNode rightOfValue) {
        // TODO (Checkpoints 2, 3, and 4): write the split method.
        return null;
    }

    // ==================================================================
    // Everything below this line is complete. You do not need to change it.
    // ==================================================================

    /** Returns true when value is somewhere in the tree. */
    public boolean contains(int value) {
        TwoThreeNode node = root;
        while (node != null) {
            if (value == node.getValue(0)
                    || (node.getNumValues() == 2 && value == node.getValue(1))) {
                return true;
            }
            if (node.isLeaf()) {
                return false;
            }
            if (value < node.getValue(0)) {
                node = node.getChild(0);
            } else if (node.getNumValues() == 1 || value < node.getValue(1)) {
                node = node.getChild(1);
            } else {
                node = node.getChild(2);
            }
        }
        return false;
    }

    /** Returns the values in inorder (sorted) order, separated by spaces. */
    public String inorder() {
        StringBuilder sb = new StringBuilder();
        inorder(root, sb);
        return sb.toString().trim();
    }

    private void inorder(TwoThreeNode node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        // left, small, middle, large, right
        inorder(node.getChild(0), sb);
        sb.append(node.getValue(0)).append(' ');
        inorder(node.getChild(1), sb);
        if (node.getNumValues() == 2) {
            sb.append(node.getValue(1)).append(' ');
            inorder(node.getChild(2), sb);
        }
    }

    /**
     * Prints the tree one level per line, for example:
     *   Level 1: [16 31]
     *   Level 2: [14] [21] [36 42]
     */
    public void printTree() {
        if (root == null) {
            System.out.println("(empty tree)");
            return;
        }
        ArrayList<TwoThreeNode> level = new ArrayList<>();
        level.add(root);
        int depth = 1;
        while (!level.isEmpty()) {
            StringBuilder line = new StringBuilder("Level " + depth + ":");
            ArrayList<TwoThreeNode> next = new ArrayList<>();
            for (TwoThreeNode node : level) {
                line.append(" [").append(node.getValue(0));
                if (node.getNumValues() == 2) {
                    line.append(' ').append(node.getValue(1));
                }
                line.append(']');
                for (int i = 0; i <= node.getNumValues(); i++) {
                    if (node.getChild(i) != null) {
                        next.add(node.getChild(i));
                    }
                }
            }
            System.out.println(line);
            level = next;
            depth++;
        }
    }
}
