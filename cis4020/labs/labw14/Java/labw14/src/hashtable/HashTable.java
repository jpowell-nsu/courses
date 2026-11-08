package hashtable;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Open-addressing hash table of nonnegative integer keys, using double hashing.
 * Lab W14 starter code. See the lab handout for what each method must do.
 */
public class HashTable {

	private static final int EMPTY = -1;               // slot has never held a key
	private static final int DELETED = -2;             // slot held a key that was removed
	private static final double MAX_LOAD = 2.0 / 3.0;  // grow when (keys + deleted) / size would pass this

	private int[] table;     // the slots
	private int count;       // number of keys in the table
	private int deleted;     // number of DELETED markers in the table
	private int lastProbes;  // probes used by the most recent search

	public HashTable(int size) {
		table = new int[size];
		clear();
	}

	public void clear() {

	}

	private int h(int x) {

		return 0;
	}

	private int h2(int x) {

		return 0;
	}

	private void rehash(int newSize) {

	}

	private static int nextPrime(int n) {

		return n;
	}

	public boolean add(int x) {

		return false;
	}

	public boolean remove(int x) {

		return false;
	}

	public boolean find(int x) {

		return false;
	}

	public int probesFor(int x) {

		return 0;
	}

	public int getSize() {
		return table.length;
	}

	public double loadFactor() {

		return 0.0;
	}

	public double successful() {

		return 0.0;
	}

	public double unsuccessful() {

		return 0.0;
	}

	public void information() {
		System.out.printf("Table Size  : %5d\n", table.length);
		System.out.printf("Count       : %5d\n", count);
		System.out.printf("Deleted     : %5d\n", deleted);
		System.out.printf("Load Factor : %5.2f\n", loadFactor());
		System.out.printf("Successful  : %5.2f\n", successful());
		System.out.printf("Unsuccessful: %5.2f\n", unsuccessful());
	}

	public void dump(String filename) throws IOException {
		FileWriter fw = new FileWriter(filename);
		PrintWriter pw = new PrintWriter(fw);
		for (int i = 0; i < table.length; i++) {
			String s = table[i] == EMPTY ? "empty" : table[i] == DELETED ? "deleted" : String.valueOf(table[i]);
			pw.printf("Slot %5d: %s\n", i, s);
		}
		pw.close();
		fw.close();
	}
}
