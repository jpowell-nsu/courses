package linkedlist;

/*********************************************************
Referenced-based implementation of the ADT list. This code is 
a slight modification to the book's code.
**********************************************************/
public class LinkedList<T> implements ListInterface<T> {

	private Node<T> head;		// points to first item
	private int numItems; 	// number of items in list

	public LinkedList() {
		numItems = 0;
		head = null;
	}  // end default constructor

	public boolean isEmpty() {
		return (numItems == 0);
	}  // end isEmpty

	public int size() {
		return numItems;
	}  // end size

	public void display() {
		Node<T> curr = head;

		while (curr != null) {
			System.out.printf("current: %-24s|%-12s|%-24s\n", curr, curr.item, curr.next);
			curr = curr.next;
		}
	}

	private Node<T> find(int index) {
		Node<T> curr = head;

		for (int skip = 0; skip < index; skip++) {
			curr = curr.next;
		}
		
		return curr;
	} // end find

	public T get(int index) throws ListIndexOutOfBoundsException {
		if (index >= 0 && index < numItems) {
			Node<T> curr = find(index);		// get node reference
			T dataItem = curr.item;			// get the item
			return dataItem;
		} else {
			throw new ListIndexOutOfBoundsException("List index out of bounds on get");
		}
	} // end get

	public void add(int index, T item) throws ListIndexOutOfBoundsException {
		if (index >= 0 && index < numItems+1) {
			if (index == 0) {	// insert the new node at beginning of list
				Node<T> newNode = new Node<T>(item, head);
				head = newNode;
			} else {	// insert the new node after the node that prev references
				Node<T> prev = find(index-1);
				Node<T> newNode = new Node<T>(item, prev.next);
				prev.next = newNode;
			}
			numItems++;
		} else {
			throw new ListIndexOutOfBoundsException("List index out of bounds on add");
		}
	}  // end add

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
	}  // end remove

	public void removeAll() {
		head = null;	// causes list to be unreachable and marked for garbage collection
		numItems = 0;
	} // end removeAll
	
}
