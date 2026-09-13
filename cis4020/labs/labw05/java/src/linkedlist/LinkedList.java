package linkedlist;

/* ********************************************************
Referenced-based implementation of the ADT list. This code is 
a slight modification to the book's code.
********************************************************* */
public class LinkedList<T> implements ListInterface<T> {

	private Node<T> head;	// points to first item
	private int numItems; 	// number of items in list

	public LinkedList() {
		numItems = 0;
		head = null;
	}

	public boolean isEmpty() {
		return (numItems == 0);
	}

	public int size() {
		return numItems;
	}

	public void display() {
		Node<T> curr = head;

		while (curr != null) {
			System.out.printf("%s\n", curr.item);
			curr = curr.next;
		}
	}

	private Node<T> find(int index) {
		Node<T> curr = head;

		for (int skip = 0; skip < index; skip++) {
			curr = curr.next;
		}
		
		return curr;
	}

	public T get(int index) throws ListIndexOutOfBoundsException {
		if (index >= 0 && index < numItems) {
			Node<T> curr = find(index);		// get node reference
			T dataItem = curr.item;			// get the item
			return dataItem;
		} else {
			throw new ListIndexOutOfBoundsException("List index out of bounds on get");
		}
	}

	public void add(int index, T item) throws ListIndexOutOfBoundsException {
		if (index >= 0 && index < numItems+1) {
			Node<T> newNode;
			if (index == 0) {	// insert the new node at beginning of list
				newNode = new Node<T>(item, null, null);
				if (head == null) {
					head = newNode;
				} else {
					newNode.next = head;
					head.prev = newNode;
					head = newNode;
				}
			} else {	// insert the new node after the node that prev references
				Node<T> curr = null;
				index--;
				for (curr = head; index != 0 && curr != null; curr = curr.next, index--)
					;
				newNode = new Node<T>(item, curr, curr.next);
				curr.next = newNode;
				if (newNode.next != null) {
					newNode.next.prev = newNode;
				}
			}
			numItems++;
		} else {
			throw new ListIndexOutOfBoundsException("List index out of bounds on add");
		}
	}

	public void remove(int index) throws ListIndexOutOfBoundsException {
		if (index >= 0 && index < numItems) {
			if (index == 0) {// delete the first node from the list
				head = head.next;
			} else {	// delete the node after the node that prev references
				Node<T> prev = find(index-1);
				Node<T> curr = prev.next;
				prev.next = curr.next;
			}
			numItems--;
		} else {
			throw new ListIndexOutOfBoundsException("List index out of bounds on remove");
		}
	}

	public void removeAll() {
		head = null;	// causes list to be unreachable and marked for garbage collection
		numItems = 0;
	}
	
}
