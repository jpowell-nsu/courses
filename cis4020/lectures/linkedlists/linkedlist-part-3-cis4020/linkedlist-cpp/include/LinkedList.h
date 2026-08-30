#ifndef LINKED_LIST_H
#define LINKED_LIST_H

#include <iostream>
#include <stdexcept>
#include <iomanip>
#include <sstream>
#include <string>
#include "Node.h"

template <typename T>
class LinkedList {
private:
    Node<T>* head;
    static std::string ptrLabel(const Node<T>* p);

public:
    LinkedList();
    ~LinkedList();

    bool isEmpty() const;
    void addFirst(const T& value);
    void addLast(const T& value);
    T retrieve(int position) const;
    bool remove(const T& value);
    void display() const;
    void walk() const;
    void destroy();
};

// Template member functions have to be visible wherever the class is used,
// so their definitions live in LinkedList.tpp and are included here, at the
// bottom of the header, instead of being compiled separately into a .cpp
// file the way a non-template class's methods would be.
#include "LinkedList.tpp"

#endif
