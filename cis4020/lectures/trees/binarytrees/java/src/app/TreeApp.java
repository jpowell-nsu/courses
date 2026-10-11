package app;

import java.util.Random;
import bst.BST;
import bst.BSTException;
import items.Droid;
import items.Item;
import items.Person;

public class TreeApp {

	public static void main(String args[]) {
		Random rand = new Random();

		BST<Item, Integer> items = new BST<Item, Integer>();
		BST<Droid, String> droids = new BST<Droid, String>();
		BST<Person, Integer> people = new BST<Person, Integer>();

		// ------------------------------------------------------------
		// Items: 100 random integers from 0 to 99
		// ------------------------------------------------------------
		System.out.println("=== Items (100 random integers) ===");
		for(int i = 0; i < 100; i++) {
			int temp = rand.nextInt(100);
			Item item = new Item(temp, temp);
			items.add(item);
		}
		items.inorder();
		System.out.println();
		System.out.println();

		// ------------------------------------------------------------
		// Droids: the key is a String, so the tree orders them
		// alphabetically by name
		// ------------------------------------------------------------
		System.out.println("=== Droids ===");
		Droid d = new Droid("IG-88");
		droids.add(d);
		d = new Droid("R2-D2");
		droids.add(d);
		d = new Droid("B1");
		droids.add(d);
		d = new Droid("IT-O");
		droids.add(d);
		d = new Droid("BB-8");
		droids.add(d);
		droids.inorder();
		System.out.println();
		System.out.println("Root: " + droids.peekRoot());

		// search for a key that is in the tree
		Droid found = droids.search("R2-D2");
		System.out.println("Search R2-D2: found " + found.getName());

		// search for a key that is not in the tree
		try {
			droids.search("C-3PO");
			System.out.println("Search C-3PO: found");
		} catch(BSTException e) {
			System.out.println("Search C-3PO: " + e.getMessage());
		}
		System.out.println();

		// ------------------------------------------------------------
		// People: the key is an Integer ID; remove them one at a time
		// ------------------------------------------------------------
		System.out.println("=== People ===");
		Person p = new Person(4, "Black Widow", "867-5309");
		people.add(p);
		p = new Person(5, "Captain America", "606-0842");
		people.add(p);
		p = new Person(3, "Iron Man", "777-9311");
		people.add(p);
		p = new Person(2, "Hulk", "489-4608");
		people.add(p);
		p = new Person(6, "Thor", "853-3937");
		people.add(p);
		p = new Person(1, "Ant-Man", "842-3089");
		people.add(p);
		System.out.println("Root: " + people.peekRoot());
		people.inorder();
		System.out.println();

		Person who = people.search(2);
		System.out.println("Search 2: " + who.getName() + ", phone " + who.getPhone());
		System.out.println();

		int[] removeOrder = {3, 1, 5, 4, 2, 6};
		for(int id : removeOrder) {
			people.remove(id);
			System.out.println("After removing " + id + ":");
			people.inorder();
			System.out.println();
		}
		System.out.println("Empty after removing everyone? " + people.isEmpty());

		// operations on an empty tree should report an error, not crash
		try {
			people.remove(4);
		} catch(BSTException e) {
			System.out.println("Remove 4 from empty tree: " + e.getMessage());
		}
		try {
			people.peekRoot();
		} catch(BSTException e) {
			System.out.println("Peek at empty tree: " + e.getMessage());
		}
	}
}
