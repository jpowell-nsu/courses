#pragma once

#include <iostream>
#include <stdexcept>
#include <string>

// Thrown when dequeue or peek is attempted on an empty queue. Mirrors
// the book's QueueException, which extends RuntimeException in Java.
class QueueException : public std::runtime_error {
public:
	explicit QueueException(const std::string& message) : std::runtime_error(message) {}
};

/*********************************************************
Reference-based (linked) implementation of the ADT queue, using the
classic single-pointer trick: back->next always points at the front,
so the whole queue is reachable from just one reference, back, even
though the chain is actually circular. A port of the Java version to
C++, using a template so the queue can hold any type without a cast.
**********************************************************/
template <typename T>
class Queue {
public:
	Queue() : back(nullptr) {}

	// This class owns its nodes through a raw pointer, so copying has to
	// be handled explicitly or disabled outright. Disabled here, the
	// same choice made for the stack and list ports.
	Queue(const Queue&) = delete;
	Queue& operator=(const Queue&) = delete;

	// This destructor matters more here than it did for the stack. The
	// Java version's dequeueAll can get away with just back = null,
	// because its garbage collector reclaims cycles as well as simple
	// chains. C++ has no such safety net: setting back to nullptr
	// without first walking the ring and deleting every node would
	// orphan the entire ring in memory, permanently, since nothing
	// would ever hold a reference to it again to free it later.
	~Queue() {
		dequeueAll();
	}

	bool isEmpty() const {
		return (back == nullptr);
	}

	void enqueue(const T& newItem) {
		Node* newNode = new Node(newItem);

		if (isEmpty()) {
			newNode->next = newNode;
		} else {
			newNode->next = back->next;
			back->next = newNode;
		}
		back = newNode;
	}

	T dequeue() {
		if (!isEmpty()) {
			Node* front = back->next;
			T item = front->item;
			if (front == back) {
				back = nullptr;
			} else {
				back->next = front->next;
			}
			delete front;
			return item;
		} else {
			throw QueueException("QueueException on dequeue: queue empty");
		}
	}

	void dequeueAll() {
		// Repeatedly dequeuing is the simplest way to guarantee every
		// node in the ring actually gets deleted, one at a time, rather
		// than trying to write a separate ring-walking free loop here
		// and risk it drifting out of sync with dequeue() later.
		while (!isEmpty()) {
			dequeue();
		}
	}

	T peek() const {
		if (!isEmpty()) {
			return back->next->item;
		} else {
			throw QueueException("Queue exception on peek queue empty");
		}
	}

	void display() const {
		std::cout << "f<-[ ";
		if (back != nullptr) {
			Node* temp = back->next;
			while (temp != back) {
				std::cout << temp->item << " ";
				temp = temp->next;
			}
			std::cout << temp->item << " ";
		}
		std::cout << "]<-b" << std::endl;
	}

private:
	struct Node {
		T item;
		Node* next;

		explicit Node(const T& newItem) : item(newItem), next(nullptr) {}
	};

	Node* back;
};
