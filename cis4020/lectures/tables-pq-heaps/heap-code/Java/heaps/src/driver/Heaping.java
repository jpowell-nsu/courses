package driver;

import java.util.Scanner;

import heap.HeapArrayBased;
import heap.HeapException;
import heap.HeapInterface;
import heap.HeapReferenceBased;
import heap.HeapType;
import items.Item;

public class Heaping {

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		int value1;
		int value2;

		// choose the kind of heap and how it is stored
		char kind = readChoice(scnr, "Select (m)axheap or mi(n)heap: ");
		HeapType type = (kind == 'n') ? HeapType.MIN : HeapType.MAX;
		char storage = readChoice(scnr, "Select (a)rray-based or (r)eference-based: ");

		HeapInterface<Item, Integer> theHeap;
		if(storage == 'r') {
			theHeap = new HeapReferenceBased<Item, Integer>(type);
		} else {
			theHeap = new HeapArrayBased<Item, Integer>(31, type); // max size 31
		}
		System.out.println("Using " + (storage == 'r' ? "a reference-based " : "an array-based ")
				+ (type == HeapType.MIN ? "minheap" : "maxheap"));

		int[] start = {70, 40, 50, 20, 60, 100, 80, 30, 10, 90};
		for(int key : start) { // insert 10 items
			theHeap.insert(new Item(key));
		}
		theHeap.displayHeap();

		char choice = readChoice(scnr, "Select (i)nsert (d)elete (p)eek (s)how (c)hange (q)uit: ");
		while(choice != 'q') {
			try {
				switch(choice) {
					case 's': // display
						theHeap.displayHeap();
						break;
					case 'i': // insert
						System.out.print("Enter value: ");
						value1 = Integer.parseInt(scnr.nextLine().trim());
						theHeap.insert(new Item(value1));
						theHeap.displayHeap();
						break;
					case 'd': // remove
						System.out.println("Removed " + theHeap.remove());
						theHeap.displayHeap();
						break;
					case 'p': // peek
						System.out.println("Root is " + theHeap.peek());
						break;
					case 'c': // change
						System.out.print("Enter index of item: ");
						value1 = Integer.parseInt(scnr.nextLine().trim());
						System.out.print("Enter new key value: ");
						value2 = Integer.parseInt(scnr.nextLine().trim());
						theHeap.change(value1, value2);
						theHeap.displayHeap();
						break;
					default:
						System.out.println("Invalid entry");
				}
			} catch(HeapException e) {
				System.out.println(e.getMessage());
			} catch(NumberFormatException e) {
				System.out.println("Please enter a whole number");
			}
			choice = readChoice(scnr, "Select (i)nsert (d)elete (p)eek (s)how (c)hange (q)uit: ");
		}
		scnr.close();
	}

	// prints the prompt and returns the first character typed, in lowercase
	// (a blank line or the end of input counts as quit)
	private static char readChoice(Scanner scnr, String prompt) {
		System.out.print(prompt);
		if(!scnr.hasNextLine()) {
			return 'q';
		}
		String line = scnr.nextLine().trim();
		if(line.isEmpty()) {
			return 'q';
		}
		return Character.toLowerCase(line.charAt(0));
	}

} // end Heaping
