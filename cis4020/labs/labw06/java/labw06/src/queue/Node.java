package queue;

public class Node<T> {

	protected T item;
	protected Node<T> next;

	Node(T newItem) {
		item = newItem;
	    next = null;
	}

	Node(T newItem, Node<T> nextNode) {
		item = newItem;
		next = nextNode;
	}

}
