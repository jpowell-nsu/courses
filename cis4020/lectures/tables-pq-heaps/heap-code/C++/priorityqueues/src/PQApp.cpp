#include <iostream>
#include "PriorityQueue.h"
#include "Task.h"

int main() {
    // 1. A to-do list where a bigger number means more important (maxheap)
    std::cout << "To-do list, largest priority first:\n";
    PriorityQueue<Task, int> todo;
    todo.insert(Task(3, "Read Chapter 12"));
    todo.insert(Task(9, "Finish the project"));
    todo.insert(Task(1, "Clean the desk"));
    todo.insert(Task(7, "Study for the quiz"));
    todo.insert(Task(5, "Answer email"));
    std::cout << "Next up: " << todo.peek() << "\n";
    while (!todo.isEmpty()) {
        std::cout << "  " << todo.remove() << "\n";
    }
    std::cout << "\n";

    // 2. Emergency room triage where level 1 is the most urgent (minheap).
    // The waiting room holds at most 4 patients, so it uses the array-based heap.
    std::cout << "Triage, level 1 is the most urgent:\n";
    PriorityQueue<Task, int> triage(4, HeapType::MIN);
    triage.insert(Task(4, "Sprained ankle"));
    triage.insert(Task(2, "Broken arm"));
    triage.insert(Task(5, "Common cold"));
    triage.insert(Task(1, "Heart attack"));
    try {
        triage.insert(Task(3, "Deep cut"));
    } catch (const PriorityQueueException& e) {
        std::cout << e.what() << "\n";
    }
    std::cout << "Patients waiting: " << triage.size() << "\n";
    while (!triage.isEmpty()) {
        std::cout << "  " << triage.remove() << "\n";
    }

    // removing from an empty queue reports an error, not a crash
    try {
        triage.remove();
    } catch (const PriorityQueueException& e) {
        std::cout << e.what() << "\n";
    }
    return 0;
}
