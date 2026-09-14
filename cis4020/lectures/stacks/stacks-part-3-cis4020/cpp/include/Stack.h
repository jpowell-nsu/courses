#pragma once

#include <iomanip>
#include <iostream>
#include <stdexcept>
#include <string>

// Thrown when pop or peek is attempted on an empty stack. Mirrors the
// book's StackException, which extends RuntimeException in Java.
class StackException : public std::runtime_error {
public:
	explicit StackException(const std::string& message) : std::runtime_error(message) {}
};

template <typename T>
struct Node {
	T item;
	Node<T>* next;

	explicit Node(const T& newItem) : item(newItem), next(nullptr) {}
	Node(const T& newItem, Node<T>* nextNode) : item(newItem), next(nextNode) {}
};

/*********************************************************
Reference-based (linked) implementation of the ADT stack. A port of
the Java version to C++, using a template so the stack can hold any
type without a cast, the same reason the Java side moved to generics.
**********************************************************/
template <typename T>
class Stack {
public:
	Stack() : top(nullptr) {}

	// This class owns its nodes through a raw pointer, so copying has to
	// be handled explicitly or disabled outright. Disabled here, the
	// same choice made for the list port: a default (shallow) copy would
	// leave two Stack objects pointing at, and eventually both trying to
	// delete, the same nodes.
	Stack(const Stack&) = delete;
	Stack& operator=(const Stack&) = delete;

	~Stack() {
		popAll();
	}

	bool isEmpty() const {
		return top == nullptr;
	}

	void push(const T& newItem) {
		top = new Node<T>(newItem, top);
	}

	T pop() {
		if (!isEmpty()) {
			Node<T>* temp = top;
			T item = temp->item;
			top = top->next;
			delete temp;
			return item;
		} else {
			throw StackException("StackException on pop: stack empty");
		}
	}

	void popAll() {
		while (top != nullptr) {
			Node<T>* temp = top;
			top = top->next;
			delete temp;
		}
	}

	T peek() const {
		if (!isEmpty()) {
			return top->item;
		} else {
			throw StackException("StackException on peek: stack empty");
		}
	}

	void display() const {
		display(top);
		std::cout << " " << std::endl;	// closes the "Base [" printed by the
										// base case below; the original Java
										// version never printed a closing bracket
	}

	void displayWithIdentities() const {
		Node<T>* hare = top;

		std::cout << std::left << std::setw(24) << "NODE"
				   << "|" << std::setw(12) << "ITEM"
				   << "|" << std::setw(24) << "NEXT" << "\n";
		while (hare != nullptr) {
			std::cout << std::left << std::setw(24) << hare
					   << "|" << std::setw(12) << hare->item
					   << "|" << std::setw(24) << hare->next << "\n";
			hare = hare->next;
		}
	}

private:
	Node<T>* top;

	void display(Node<T>* n) const {
		if (n == nullptr) {
			std::cout << "Base ";
		} else {
			display(n->next);
			std::cout << "(" << n->item << ") ";
		}
	}
};
