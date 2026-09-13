package driver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import droids.Droid;
import linkedlist.LinkedList;

public class DroidDriver {

	public static void main(String[] args) {
		LinkedList<Droid> droids = new LinkedList<Droid>();
		
		String filename = "files/droids.txt";
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = reader.readLine()) != null) {
				line = line.trim();
				if (!line.isEmpty()) {
					droids.add(droids.size(), new Droid(line));
				}
			}
		} catch (IOException e) {
			System.out.println("Could not read " + filename + ": " + e.getMessage());
			return;
		}
		// The try-with-resources block above closes the file automatically,
		// whether the read finishes normally or an exception is thrown.

		System.out.println("Loaded " + droids.size() + " droids from " + filename + ".");
		droids.display();

		// -----------------------------------------------------------------
		// Your testing code goes here. Use display(), the methods you add
		// in Parts 1 through 3, and your own small test cases (including an
		// empty list and a single-item list) to check your work.
		// -----------------------------------------------------------------
	}

}
