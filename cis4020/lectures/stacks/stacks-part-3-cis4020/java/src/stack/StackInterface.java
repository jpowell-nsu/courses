package stack;

public interface StackInterface<T> {

	public boolean isEmpty();

	public void popAll();

	public void push(T newItem) throws StackException;

	public T pop() throws StackException;

	public T peek() throws StackException;

}
