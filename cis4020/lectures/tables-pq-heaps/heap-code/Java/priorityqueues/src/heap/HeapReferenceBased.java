package heap;

import java.util.ArrayList;
import java.util.List;

// reference-based heap
//
// The nodes are numbered the same way as the array-based heap: the root is 0,
// and the numbers continue level by level, left to right. For node number i:
//	parent      is node (i - 1) / 2
//	left child  is node 2 * i + 1  (an odd number)
//	right child is node 2 * i + 2  (an even number)
// There is no array to index into, so nodeAt follows links from the root to
// reach a node by its number. The last node is always node size - 1, and a
// new node always goes in as node size.
public class HeapReferenceBased<T extends KeyedItem<KT>, KT extends Comparable<? super KT>>
		implements HeapInterface<T, KT> {

	private Node<T> root;
	private int size;		// number of nodes in heap
	private HeapType type;	// MAX or MIN

	public HeapReferenceBased() {
		this(HeapType.MAX); // a maxheap unless told otherwise
	}

	public HeapReferenceBased(HeapType type) {
		this.root = null;
		this.size = 0;
		this.type = type;
	}

	public boolean isEmpty() {
		return (root == null);
	}

	public int size() {
		return size;
	}

	public void removeAll() {
		root = null;
		size = 0;
	}

	// returns node number index by walking down from the root
	// note: assumes 0 <= index < size
	private Node<T> nodeAt(int index) {
		if(index == 0) {
			return root;
		}
		Node<T> parent = nodeAt((index - 1) / 2); // first find the parent
		if(index % 2 == 1) {
			return parent.left;  // odd numbers are left children
		} else {
			return parent.right; // even numbers are right children
		}
	}

	public void insert(T item) {
		if(item == null) {
			throw new HeapException("Heap Exception: Null Item");
		}
		Node<T> newNode = new Node<T>(item);

		// step 1: place the new node as the last node (node number size)
		if(root == null) {
			root = newNode;
		} else {
			Node<T> parent = nodeAt((size - 1) / 2);
			newNode.parent = parent;
			if(size % 2 == 1) {
				parent.left = newNode;
			} else {
				parent.right = newNode;
			}
		}
		size++;
		trickleUp(newNode); // step 2: trickle up
	}

	private void trickleUp(Node<T> current) {
		T bottom = current.item; // the item that is moving up

		// while not at the root and the new item outranks its parent
		while(current.parent != null && higherPriority(bottom.getKey(), current.parent.item.getKey())) {
			current.item = current.parent.item; // move the parent item down
			current = current.parent;
		}
		current.item = bottom;
	}

	public T remove() {
		if(root == null) {
			throw new HeapException("Heap Exception: Empty Heap");
		}
		T rootItem = root.item; // step 1: remove the root item

		if(size == 1) {
			root = null;
		} else {
			// step 2: place the last node's item in the root
			Node<T> last = nodeAt(size - 1);
			root.item = last.item;
			// then unlink the last node from its parent
			if(last.parent.right == last) {
				last.parent.right = null;
			} else {
				last.parent.left = null;
			}
			last.parent = null;
			trickleDown(root); // step 3: trickle down
		}
		size--;
		return rootItem;
	}

	private void trickleDown(Node<T> current) {
		T top = current.item; // the item that is moving down

		while(current.left != null) { // while the node has at least one child
			// find the child with the higher priority
			// (the larger child in a maxheap, the smaller child in a minheap)
			Node<T> higherChild = current.left;
			if(current.right != null
					&& higherPriority(current.right.item.getKey(), current.left.item.getKey())) {
				higherChild = current.right; // right child exists and wins
			}
			// stop when the child does not outrank the item moving down
			if(!higherPriority(higherChild.item.getKey(), top.getKey())) {
				break;
			}
			current.item = higherChild.item; // move the child item up
			current = higherChild;           // go down
		}
		current.item = top;
	}

	public T peek() {
		if(root == null) {
			throw new HeapException("Heap Exception: Empty Heap");
		}
		return root.item;
	}

	public void change(int index, KT newKey) {
		if(index < 0 || index >= size) {
			throw new HeapException("Heap Exception: Invalid Index");
		}
		if(newKey == null) {
			throw new HeapException("Heap Exception: Null Key");
		}
		Node<T> target = nodeAt(index);
		KT oldKey = target.item.getKey(); // remember old
		target.item.setKey(newKey);       // change to new
		if(higherPriority(newKey, oldKey)) {
			trickleUp(target);   // priority went up, so trickle it up
		} else {
			trickleDown(target); // priority went down (or stayed), so trickle it down
		}
	}

	// returns true when key1 belongs above key2:
	// larger in a maxheap, smaller in a minheap
	private boolean higherPriority(KT key1, KT key2) {
		int cmp = key1.compareTo(key2);
		if(type == HeapType.MAX) {
			return cmp > 0;
		} else {
			return cmp < 0;
		}
	}

	public void displayHeap() {
		// visit the nodes breadth-first, the same order as the array version
		List<KT> keys = new ArrayList<KT>();
		List<Node<T>> level = new ArrayList<Node<T>>();
		if(root != null) {
			level.add(root);
		}
		for(int i = 0; i < level.size(); i++) {
			Node<T> current = level.get(i);
			keys.add(current.item.getKey());
			if(current.left != null) {
				level.add(current.left);
			}
			if(current.right != null) {
				level.add(current.right);
			}
		}
		HeapPrinter.print(keys);
	}

} // end HeapReferenceBased
