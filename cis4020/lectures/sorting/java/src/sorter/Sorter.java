package sorter;

import java.util.Arrays;
import java.util.Random;

import droids.Droid;
import sorts.Sorts;

public class Sorter {

	public static void main(String[] args) {
		Random rand = new Random(100);
		Integer[] items = new Integer[50];
		
		for (int i = 0; i < items.length; i++) {
			items[i] = rand.nextInt(1000);
		}
		
		System.out.println(Arrays.toString(items));
		Sorts.bubbleSort(items);
		//Sorts.selectionSort(items);
		//Sorts.insertionSort(items);
		//Sorts.mergesort(items);
		//Sorts.quickSort(items);
		System.out.println(Arrays.toString(items));
		
		String[] droidNames = {
				"R2-D2", "C-3PO", "BB-8", "K-2SO", "IG-11",
				"L3-37", "D-O", "Chopper", "R5-D4", "IG-88"	
		};
		int[] droidIDs = {792, 540, 162, 966, 420, 342, 340, 565, 694, 186};
		
		Droid[] droids = new Droid[10];
		for (int i = 0; i < droids.length; i++) {
			droids[i] = new Droid(droidIDs[i], droidNames[i]);
		}
		
		System.out.println(Arrays.toString(droids));
		Sorts.bubbleSort(droids);
		//Sorts.selectionSort(droids);
		//Sorts.insertionSort(droids);
		//Sorts.mergesort(droids);
		//Sorts.quickSort(droids);
		System.out.println(Arrays.toString(droids));
	}

}
