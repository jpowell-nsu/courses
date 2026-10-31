package heap;

// a node in the reference-based heap
// The parent link lets an item trickle up without starting over at the root.
class Node<T> {
	T item;
	Node<T> left;
	Node<T> right;
	Node<T> parent;

	public Node(T item) {
		this.item = item;
		this.left = null;
		this.right = null;
		this.parent = null;
	}

} // end Node
