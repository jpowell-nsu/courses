package linkedlist;

public class Node<T> {
	
	protected T item;
	protected Node<T> prev;
	protected Node<T> next;

	Node(T newItem) {
		item = newItem;
		prev = null;
	    next = null;
	}

	Node(T newItem, Node<T> prevNode, Node<T> nextNode) {
		item = newItem;
		prev = prevNode;
		next = nextNode;
	}

}
