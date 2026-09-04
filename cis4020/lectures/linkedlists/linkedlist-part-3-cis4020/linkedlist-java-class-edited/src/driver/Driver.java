package driver;

import droids.Droid;
import linkedlist.LinkedList;

public class Driver {

	public static void main(String[] args) {
		int[] values = {18, 10, 5, 100, 19, 3, 12};
		
		LinkedList<Integer> list = new LinkedList<>();
		
		for (int i = 0; i < values.length; i++) {
			list.addFirst(values[i]);
		}
		
		list.display();
		list.walk();
		list.reverse();
	/*
		LinkedList<Droid> droids = new LinkedList<>();

		Droid droid = new Droid("R2-D2", 1138);
		droids.addLast(droid);
		
		droid = new Droid("BB-8", 42);
		droids.addLast(droid);
		
		droid = new Droid("C-3PO", 66);
		droids.addLast(droid);
		
		droid = new Droid("R5-D4", 2187);
		droids.addLast(droid);
		
		droids.display();
		droids.walk();

		LinkedList<Object> objects = new LinkedList<>();
		droid = new Droid("BB-8", 42);
		objects.addFirst(droid);
		
		objects.addLast(42);
		
		objects.display();
		objects.walk();
		*/
	}
}
