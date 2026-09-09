package linkedlist;

public class Node<T> {
	
	protected T item;
	protected Node<T> next;

	Node(T newItem) {
		item = newItem;
	    next = null;
	} // end constructor

	Node(T newItem, Node<T> nextNode) {
		item = newItem;
		next = nextNode;
	} // end constructor

} // end class Node
