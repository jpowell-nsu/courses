package stack;

public class Stack<T> implements StackInterface<T> {

	private Node<T> top;

	public Stack() {
		top = null;
	}

	public boolean isEmpty() {
		return top == null;
	}

	public void push(T newItem) {
		top = new Node<T>(newItem, top);
	}

	public T pop() throws StackException {
		if (!isEmpty()) {
			Node<T> temp = top;
			top = top.next;
			return temp.item;
		} else {
			throw new StackException("StackException on pop: stack empty");
		}
	}

	public void popAll() {
		top = null;
	}

	public T peek() throws StackException {
		if (!isEmpty()) {
			return top.item;
		} else {
			throw new StackException("StackException on peek: stack empty");
		}
	}

	public void display() {
		display(top);
		System.out.println("]");	// closes the "Base [" printed by the base case below;
									// the original never printed a closing bracket
	}  

	private void display(Node<T> n) {
		if (n == null) {
			System.out.print("Base [");
		} else {
			display(n.next);
			System.out.printf("(%s) ", n.item);
		}
	}
	
	public void displayWithIdentities() {
		Node<T> curr = top;
			
		System.out.printf("%-24s|%-12s|%-24s\n", "NODE", "ITEM", "NEXT");
		while (curr != null) {
			System.out.printf("%-24s|%-12s|%-24s\n", curr, curr.item, curr.next);
			curr = curr.next;
		}
	}
}
