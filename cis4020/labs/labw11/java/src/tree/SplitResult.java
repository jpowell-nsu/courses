package tree;

/**
 * What a split hands back to the level above it: the value that moves up,
 * and the new node that belongs immediately to the right of that value.
 *
 * This class is complete. You do not need to change it.
 */
public class SplitResult {
	
    private int promotedValue;
    private TwoThreeNode newRightNode;

    public SplitResult(int promotedValue, TwoThreeNode newRightNode) {
        this.promotedValue = promotedValue;
        this.newRightNode = newRightNode;
    }

    public int getPromotedValue() {
        return promotedValue;
    }

    public TwoThreeNode getNewRightNode() {
        return newRightNode;
    }
}
