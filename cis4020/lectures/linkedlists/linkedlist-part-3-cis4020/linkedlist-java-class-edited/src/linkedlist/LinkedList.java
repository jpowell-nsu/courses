package linkedlist;

public class LinkedList<T> {
    
    private Node<T> head;

    public boolean isEmpty() {
        return head == null;
    }

    // Adds to the front. Cheapest place to insert, since there is no tail reference.
    public void addFirst(T value) {
        Node<T> newNode = new Node<>(value);
        newNode.setNext(head);
        head = newNode;
    }
    
    public void addLast(T value) {
        Node<T> newNode = new Node<>(value);
        if (isEmpty()) {
            head = newNode;
            return;
        }
        Node<T> current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(newNode);
    }    

    // 0-based position. Throws if the list is empty or the position does not exist.
    public T retrieve(int position) {
        if (position < 0) {
            throw new IndexOutOfBoundsException("position must be non-negative");
        }
        Node<T> current = head;
        int index = 0;
        while (current != null) {
            if (index == position) {
                return current.getData();
            }
            current = current.getNext();
            index++;
        }
        throw new IndexOutOfBoundsException("position is past the end of the list");
    }

    // Removes the first node whose data equals value. Relies on T's equals().
    public boolean remove(T value) {
        if (isEmpty()) {
            return false;
        }
        if (head.getData().equals(value)) {
            head = head.getNext();
            return true;
        }
        Node<T> current = head;
        while (current.getNext() != null && !current.getNext().getData().equals(value)) {
            current = current.getNext();
        }
        if (current.getNext() == null) {
            return false;   // value was not found
        }
        current.setNext(current.getNext().getNext());
        return true;
    }

    public void display() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.getData() + " ");
            current = current.getNext();
        }
        System.out.println();
    }
    
    public void reverse() {
    	reversedisplay(head);
    }

    private void reversedisplay(Node<T> node) {
    	if (node == null) {
    		return;
    	}
    	reversedisplay(node.getNext());
    	System.out.println(node.getData() + " ");
    	
    }
    
    // Walks the list and prints each node's own identity, its data, and the
    // identity of the node it points to next.
    public void walk() {
        Node<T> current = head;
        System.out.println("Head:" + head);
        while (current != null) {
            System.out.printf("%-24s %-11s %s\n", current, current.getData(), current.getNext());
            current = current.getNext();
        }
        System.out.println();
    }
    
    // Empties the list. Nothing references the old nodes afterward, so the
    // garbage collector reclaims them; there is no delete to call.
    public void destroy() {
        head = null;
    }
}
