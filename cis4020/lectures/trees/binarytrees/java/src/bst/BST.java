package bst;

public class BST<T extends KeyedItem<KT>, KT extends Comparable<? super KT>> {

	private Node<T> root;

	public BST() {
		root = null;
	}

	public BST(T item) {
		if(item == null) {
			throw new BSTException("BST Exception: Null Item");
		}
		root = new Node<T>(item);
	}

	public boolean isEmpty() {
		return (root == null);
	}

	public void removeAll() {
		root = null;
	}

	public T peekRoot() {
		if(root == null) {
			throw new BSTException("BST Exception: Empty Tree");
		} else {
			return root.item;
		}
	}

	public void add(T item) {
		if(item == null) {
			throw new BSTException("BST Exception: Null Item");
		}
		root = insert(root, item); // begin inserting process
	}

	private Node<T> insert(Node<T> tn, T item) {
		if(tn == null) { // position found
			tn = new Node<T>(item); // create the new node
		} else {
			Node<T> newSubtree;
			T nodeItem = tn.item;
			// search for the insertion point
			if(item.getKey().compareTo(nodeItem.getKey()) < 0) {
				// search left
				newSubtree = insert(tn.left, item);
				tn.left = newSubtree;
			} else {
				// search right
				// Duplicates: a key EQUAL to tn's key also lands here, so
				// equal keys are stored in the right subtree. This tree
				// therefore allows duplicates, with left < parent <= right.
				newSubtree = insert(tn.right, item);
				tn.right = newSubtree;
			}
		}
		return tn;
	}

	public void remove(KT searchKey) {
		root = delete(root, searchKey);
	}

	private Node<T> delete(Node<T> tn, KT searchKey) {
		Node<T> newSubtree;

		if(tn == null) {
			throw new BSTException("BST Exception: Item Not Found");
		} else {
			T item = tn.item;
			int cmp = searchKey.compareTo(item.getKey()); // compare once
			if(cmp == 0) {
				tn = deleteNode(tn);
			} else if(cmp < 0) {
				// search left
				newSubtree = delete(tn.left, searchKey);
				tn.left = newSubtree;
			} else {
				// search right
				newSubtree = delete(tn.right, searchKey);
				tn.right = newSubtree;
			}
		}
		return tn;
	}

	private Node<T> deleteNode(Node<T> tn) {
		if(tn.left == null && tn.right == null) {
			// case 1: node is a leaf
			return null;
		} else if (tn.left == null) {
			// case 2a: one child, a right child only
			return tn.right;
		} else if (tn.right == null) {
			// case 2b: one child, a left child only
			return tn.left;
		} else {
			// case 3: two children
			//	note: go right once before it goes left
			T replacement = inorderSuccessor(tn.right);
			tn.item = replacement;
			tn.right = deleteInorderSuccessor(tn.right);
			return tn;
		}
	}

	private T inorderSuccessor(Node<T> tn) {
		if(tn.left == null) {
			return tn.item; // the replacement item
		} else {
			return inorderSuccessor(tn.left);
		}
	}

	private Node<T> deleteInorderSuccessor(Node<T> tn) {
		if(tn.left == null) {
			return tn.right;
		} else {
			tn.left = deleteInorderSuccessor(tn.left);
			return tn;
		}
	}

	public T search(KT searchKey) {
		// retrieve returns the node that holds the key, or null if the
		// key is not in the tree; .item then returns the item in the node
		Node<T> found = retrieve(root, searchKey);
		if(found == null) {
			throw new BSTException("BST Exception: Item Not Found");
		}
		return found.item;
	}

	private Node<T> retrieve(Node<T> tn, KT searchKey) {
		if(tn == null) {
			return null;
		} else {
			T item = tn.item;
			int cmp = searchKey.compareTo(item.getKey()); // compare once
			if(cmp == 0) {
				return tn;
			} else if(cmp < 0) {
				return retrieve(tn.left, searchKey);
			} else {
				return retrieve(tn.right, searchKey);
			}
		}
	}

	public void inorder() {
		inorder(root);
	}
	
	private void inorder(Node<T> tn) {
		if (tn != null) {
			inorder(tn.left);
			System.out.print(tn.item + " ");
			inorder(tn.right);
		}
	}

}
