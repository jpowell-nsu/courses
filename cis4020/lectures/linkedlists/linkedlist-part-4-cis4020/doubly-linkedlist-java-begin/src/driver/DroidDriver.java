package driver;

import droids.Droid;
import linkedlist.LinkedList;

public class DroidDriver {

	public static void main(String[] args) {
		LinkedList<Droid> droids = new LinkedList<Droid>();
		
		Droid droid = new Droid("R2-D2");
		droids.add(0, droid);
		
		droid = new Droid("BB-8");
		droids.add(0, droid);
		
		droid = new Droid("C-3PO");
		droids.add(0, droid);
		
		droid = new Droid("R5-D4");
		droids.add(0, droid);
		
		droids.display();
		
		for (int i = 0; i < droids.size(); i++) {
			// with generics, get(i) already returns a Droid; no cast needed
			droid = droids.get(i);
			System.out.println(droid);
		}

		System.out.println();
	}

}
