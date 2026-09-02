#ifndef LINKED_LIST_TPP
#define LINKED_LIST_TPP

template <typename T>
LinkedList<T>::LinkedList() : head(nullptr) {}

template <typename T>
LinkedList<T>::~LinkedList() {
    destroy();
}

template <typename T>
bool LinkedList<T>::isEmpty() const {
    return head == nullptr;
}

template <typename T>
void LinkedList<T>::addFirst(const T& value) {
    Node<T>* newNode = new Node<T>(value);
    newNode->next = head;
    head = newNode;
}

template <typename T>
void LinkedList<T>::addLast(const T& value) {
    Node<T>* newNode = new Node<T>(value);
    if (isEmpty()) {
        head = newNode;
        return;
    }
    Node<T>* current = head;
    while (current->next != nullptr) {
        current = current->next;
    }
    current->next = newNode;
}

template <typename T>
T LinkedList<T>::retrieve(int position) const {
    if (position < 0) {
        throw std::out_of_range("position must be non-negative");
    }
    Node<T>* current = head;
    int index = 0;
    while (current != nullptr) {
        if (index == position) {
            return current->data;
        }
        current = current->next;
        index++;
    }
    throw std::out_of_range("position is past the end of the list");
}

template <typename T>
bool LinkedList<T>::remove(const T& value) {
    if (isEmpty()) {
        return false;
    }
    if (head->data == value) {
        Node<T>* toDelete = head;
        head = head->next;
        delete toDelete;
        return true;
    }
    Node<T>* current = head;
    while (current->next != nullptr && !(current->next->data == value)) {
        current = current->next;
    }
    if (current->next == nullptr) {
        return false;
    }
    Node<T>* toDelete = current->next;
    current->next = toDelete->next;
    delete toDelete;
    return true;
}

template <typename T>
void LinkedList<T>::display() const {
    Node<T>* current = head;
    while (current != nullptr) {
        std::cout << current->data << " ";
        current = current->next;
    }
    std::cout << std::endl;
}

// Walks the list and prints each node's own address, its data, and the
// address it points to next, so you can see the chain of pointers the same
// way the Java walk() method shows the chain of object identities.
template <typename T>
void LinkedList<T>::walk() const {
    std::cout << std::left;
    std::cout << "head: " << ptrLabel(head) << std::endl;
    Node<T>* current = head;
    while (current != nullptr) {
        std::ostringstream dataStream;
        dataStream << current->data;
        std::cout << std::setw(16) << ptrLabel(current)
                  << std::setw(14) << dataStream.str()
                  << ptrLabel(current->next) << std::endl;
        current = current->next;
    }
    std::cout << std::endl;
}

// needed by the walk method
template <typename T>
std::string LinkedList<T>::ptrLabel(const Node<T>* p) {
    if (p == nullptr) {
        return "nullptr";
    }
    std::ostringstream oss;
    oss << p;
    return oss.str();
}

template <typename T>
void LinkedList<T>::destroy() {
    while (head != nullptr) {
        Node<T>* temp = head;
        head = head->next;
        delete temp;
    }
}

#endif
