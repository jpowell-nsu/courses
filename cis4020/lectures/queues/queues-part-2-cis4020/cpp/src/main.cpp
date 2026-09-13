#include <iostream>
#include <string>
#include <vector>
 
#include "Queue.h"
 
/*********************************************************
Demonstrates the ADT queue with the "Hot Potato" elimination game
(a version of the classic Josephus problem). Players stand in a
circle passing a potato. Every time the count is reached, whoever
is holding the potato is out. The queue models the circle: passing
the potato is a dequeue immediately followed by an enqueue, and
being eliminated is a dequeue with no matching enqueue.
**********************************************************/
 
std::string play(const std::vector<std::string>& players, int passCount) {
	Queue<std::string> queue;
	for (const std::string& player : players) {
		queue.enqueue(player);
	}
 
	// The Queue class has no size() method, so the driver keeps its own
	// count of how many players are still in the circle.
	int remaining = static_cast<int>(players.size());
 
	while (remaining > 1) {
		for (int i = 0; i < passCount - 1; i++) {
			queue.enqueue(queue.dequeue());	// pass the potato: to the back of the line
		}
		std::string eliminated = queue.dequeue();	// holding it when the count runs out
		remaining--;
		std::cout << eliminated << " is out" << std::endl;
	}
 
	return queue.dequeue();	// the one player left is the winner
}
 
int main() {
	std::vector<std::string> players = {"Alice", "Ben", "Cara", "Dev", "Ellis", "Farah", "Gus"};
	int passCount = 3;
 
	std::cout << "Players: [";
	for (std::size_t i = 0; i < players.size(); i++) {
		std::cout << players[i];
		if (i + 1 < players.size()) {
			std::cout << ", ";
		}
	}
	std::cout << "]" << std::endl;
	std::cout << "Passing the potato; every " << passCount << " passes, someone is eliminated:" << std::endl;
	std::cout << std::endl;
 
	std::string winner = play(players, passCount);
 
	std::cout << std::endl;
	std::cout << winner << " wins!" << std::endl;
 
	return 0;
}
