package heap;

import java.util.List;

// Prints a heap for both implementations. The caller passes the keys in
// breadth-first order (root first, then each level from left to right),
// which is the same as array order.
// Layout adapted from Data Structures and Algorithms in Java, 2nd ed, Robert Lafore
class HeapPrinter {

	static void print(List<?> keys) {
		int size = keys.size();

		// array format
		System.out.print("Heap: ");
		for(int i = 0; i < size; i++) {
			System.out.print(keys.get(i) + " ");
		}
		System.out.println();

		// tree format (lines up best for 31 or fewer two-digit keys)
		int nBlanks = 32;
		int itemsPerRow = 1;
		int column = 0;
		int j = 0; // current item
		System.out.println("--------------------------------------------------------------");
		while(j < size) { // for each heap item
			if(column == 0) { // first item in row?
				for(int k = 0; k < nBlanks; k++) { // preceding blanks
					System.out.print(" ");
				}
			}
			System.out.print(keys.get(j)); // display item
			j++;
			if(j == size) { // done
				break;
			}
			column++;
			if(column == itemsPerRow) { // end of row?
				nBlanks /= 2; // half the blanks
				itemsPerRow *= 2; // twice the items
				column = 0; // start over on
				System.out.println(); // new row
			} else { // next item on row
				for(int k = 0; k < nBlanks * 2 - 2; k++) {
					System.out.print(" "); // interim blanks
				}
			}
		}
		System.out.println();
		System.out.println("--------------------------------------------------------------");
	}

} // end HeapPrinter
