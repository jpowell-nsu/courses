package queue;

public class Queue<T> implements QueueInterface<T> {

	private Node<T> back;

	public Queue() {
		back = null;
	}

	public boolean isEmpty() {
		return (back == null);
	}

	public void enqueue(T newItem) {
		Node<T>  newNode = new Node<T>(newItem);

	    if (isEmpty()) {
	      newNode.next = newNode;
	    } else {
	      newNode.next = back.next;
	      back.next = newNode;
	    }
	    back = newNode;
	}

	public T dequeue() throws QueueException {
		if (!isEmpty()) {
			Node<T> front = back.next;
		    if (front == back) {
		    	back = null;
		    } else {
		        back.next = front.next;
		    } 
		    return front.item;
		} else {
			throw new QueueException("QueueException on dequeue: queue empty");
		} 
	}

	public void dequeueAll() {
		back = null;
	}

	public T peek() throws QueueException {
		if (!isEmpty()) {
			return back.next.item;
		} else {
			throw new QueueException("Queue exception on peek queue empty");
	    }
	}
	  
	public void display() {
		System.out.print("f<-[ ");
		if (back != null) {
			Node<T> curr = back.next;
			while (curr != back) {
				System.out.print(curr.item + " ");
				curr = curr.next;
			}
			System.out.print(curr.item + " ");
		}
		System.out.println("]<-b");
	}

}
