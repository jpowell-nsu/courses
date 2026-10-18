package tree;

/**
 * One node of a 2-3 tree.
 *
 * A node holds one or two values, kept in ascending order. A leaf has no
 * children. An internal node with one value has exactly two children, and an
 * internal node with two values has exactly three children.
 *
 * This class is complete. You do not need to change it.
 */
public class TwoThreeNode {
	
    private int[] values = new int[2];
    private TwoThreeNode[] children = new TwoThreeNode[3];
    private int numValues;

    /** Creates a leaf holding a single value. */
    public TwoThreeNode(int value) {
        values[0] = value;
        numValues = 1;
    }

    /** Returns how many values this node holds, 1 or 2. */
    public int getNumValues() {
        return numValues;
    }

    /** Returns the value at position index (0 for the smaller, 1 for the larger). */
    public int getValue(int index) {
        return values[index];
    }

    /** Returns the child at position index (0 is the leftmost), or null. */
    public TwoThreeNode getChild(int index) {
        return children[index];
    }

    /** A node is a leaf when it has no children. */
    public boolean isLeaf() {
        return children[0] == null;
    }

    /** A node is full when it already holds two values. */
    public boolean isFull() {
        return numValues == 2;
    }

    /**
     * Adds a value to a node that is NOT full, keeping the values in order.
     *
     * rightOfValue becomes the child immediately to the right of the new
     * value, and any children after it shift one place to the right. Pass
     * null when this node is a leaf.
     */
    public void addValue(int value, TwoThreeNode rightOfValue) {
        if (isFull()) {
            throw new IllegalStateException("addValue called on a full node");
        }
        if (value < values[0]) {
            values[1] = values[0];
            values[0] = value;
            children[2] = children[1];
            children[1] = rightOfValue;
        } else {
            values[1] = value;
            children[2] = rightOfValue;
        }
        numValues = 2;
    }

    // ------------------------------------------------------------------
    // Low-level setters. Use these when you rebuild a node during a split.
    // ------------------------------------------------------------------

    /** Sets the value at position index. */
    public void setValue(int index, int value) {
        values[index] = value;
    }

    /** Sets how many values this node holds, 1 or 2. */
    public void setNumValues(int count) {
        numValues = count;
    }

    /** Sets the child at position index. Pass null to remove a child. */
    public void setChild(int index, TwoThreeNode child) {
        children[index] = child;
    }
}
