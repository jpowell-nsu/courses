package driver;

import java.util.Arrays;
import java.util.List;

import queue.Queue;

/*********************************************************
Demonstrates the ADT queue with the "Hot Potato" elimination game
(a version of the classic Josephus problem). Players stand in a
circle passing a potato. Every time the count is reached, whoever
is holding the potato is out. The queue models the circle: passing
the potato is a dequeue immediately followed by an enqueue, and
being eliminated is a dequeue with no matching enqueue.
**********************************************************/

public class Driver {

	public static void main(String[] args) {
		List<String> players = Arrays.asList("Alice", "Ben", "Cara", "Dev", "Ellis", "Farah", "Gus");
		int passCount = 3;
 
		System.out.println("Players: " + players);
		System.out.println("Passing the potato; every " + passCount + " passes, someone is eliminated:");
		System.out.println();
 
		String winner = play(players, passCount);
 
		System.out.println();
		System.out.println(winner + " wins!");
	}

	public static String play(List<String> players, int passCount) {
		Queue<String> queue = new Queue<String>();
		for (String player : players) {
			queue.enqueue(player);
		}
 
		// The Queue class has no size() method, so the driver keeps its
		// own count of how many players are still in the circle.
		int remaining = players.size();
 
		while (remaining > 1) {
			for (int i = 0; i < passCount - 1; i++) {
				queue.enqueue(queue.dequeue());	// pass the potato: to the back of the line
			}
			String eliminated = queue.dequeue();	// holding it when the count runs out
			remaining--;
			System.out.println(eliminated + " is out");
		}
 
		return queue.dequeue();	// the one player left is the winner
	}
	
}
