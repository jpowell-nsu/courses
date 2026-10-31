package heap;

public interface HeapInterface<T extends KeyedItem<KT>, KT extends Comparable<? super KT>> {

	// returns true if the heap has no items
	public boolean isEmpty();

	// returns the number of items in the heap
	public int size();

	// removes every item from the heap
	public void removeAll();

	// adds item to the heap
	// throws HeapException if item is null or the heap is full
	public void insert(T item);

	// removes and returns the item with the highest priority (the root)
	// throws HeapException if the heap is empty
	public T remove();

	// returns the item with the highest priority without removing it
	// throws HeapException if the heap is empty
	public T peek();

	// changes the key of the item at index (breadth-first order, root is 0)
	// and moves the item up or down to restore the heap
	// throws HeapException if index is out of range or newKey is null
	public void change(int index, KT newKey);

	// prints the heap, first in array order and then as a tree
	public void displayHeap();

} // end HeapInterface
