package driver;

import heap.HeapType;
import items.Task;
import pq.PriorityQueue;
import pq.PriorityQueueException;

public class PQApp {

	public static void main(String[] args) {

		// 1. A to-do list where a bigger number means more important (maxheap)
		System.out.println("To-do list, largest priority first:");
		PriorityQueue<Task, Integer> todo = new PriorityQueue<Task, Integer>();
		todo.insert(new Task(3, "Read Chapter 12"));
		todo.insert(new Task(9, "Finish the project"));
		todo.insert(new Task(1, "Clean the desk"));
		todo.insert(new Task(7, "Study for the quiz"));
		todo.insert(new Task(5, "Answer email"));
		System.out.println("Next up: " + todo.peek());
		while(!todo.isEmpty()) {
			System.out.println("  " + todo.remove());
		}
		System.out.println();

		// 2. Emergency room triage where level 1 is the most urgent (minheap).
		// The waiting room holds at most 4 patients, so it uses the array-based heap.
		System.out.println("Triage, level 1 is the most urgent:");
		PriorityQueue<Task, Integer> triage = new PriorityQueue<Task, Integer>(4, HeapType.MIN);
		triage.insert(new Task(4, "Sprained ankle"));
		triage.insert(new Task(2, "Broken arm"));
		triage.insert(new Task(5, "Common cold"));
		triage.insert(new Task(1, "Heart attack"));
		try {
			triage.insert(new Task(3, "Deep cut"));
		} catch(PriorityQueueException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Patients waiting: " + triage.size());
		while(!triage.isEmpty()) {
			System.out.println("  " + triage.remove());
		}

		// removing from an empty queue reports an error, not a crash
		try {
			triage.remove();
		} catch(PriorityQueueException e) {
			System.out.println(e.getMessage());
		}
	}

} // end PQApp
