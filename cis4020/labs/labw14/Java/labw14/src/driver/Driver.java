package driver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import hashtable.HashTable;

/** Lab W14 driver. Starter code. See the lab handout for each part. */
public class Driver {

	// Reads every integer in a file into a list.
	private static ArrayList<Integer> readKeys(String filename) throws IOException {
		ArrayList<Integer> keys = new ArrayList<>();
		Scanner input = new Scanner(new File(filename));
		while (input.hasNextInt()) {
			keys.add(input.nextInt());
		}
		input.close();
		return keys;
	}

	public static void main(String[] args) throws IOException {
		// Part 1: build the table, starting small so it has to grow.
		System.out.println("=== Part 1: Building the table ===");
		ArrayList<Integer> keys = readKeys("files/integers.txt");
		HashTable table = new HashTable(11);

		// Part 2: duplicates are rejected.
		System.out.println("\n=== Part 2: Duplicates ===");

		// Part 3: measured probes compared with the formulas.
		System.out.println("\n=== Part 3: Measured and formula ===");

		// Part 4: a sequence of operations on a new table.
		System.out.println("\n=== Part 4: Operations ===");

	}
}
