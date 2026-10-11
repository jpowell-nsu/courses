// TreeApp.cpp -- C++ counterpart of TreeApp.java
#include <iostream>
#include <random>
#include <string>
#include "BST.h"
#include "Item.h"
#include "Droid.h"
#include "Person.h"

int main() {
    std::random_device seed;
    std::mt19937 rand(seed());
    std::uniform_int_distribution<int> zeroTo99(0, 99);

    BST<Item, int> items;
    BST<Droid, std::string> droids;
    BST<Person, int> people;

    // ------------------------------------------------------------
    // Items: 100 random integers from 0 to 99
    // ------------------------------------------------------------
    std::cout << "=== Items (100 random integers) ===" << std::endl;
    for (int i = 0; i < 100; i++) {
        int temp = zeroTo99(rand);
        items.add(Item(temp, temp));
    }
    items.inorder();
    std::cout << std::endl << std::endl;

    // ------------------------------------------------------------
    // Droids: the key is a string, so the tree orders them
    // alphabetically by name
    // ------------------------------------------------------------
    std::cout << "=== Droids ===" << std::endl;
    droids.add(Droid("IG-88"));
    droids.add(Droid("R2-D2"));
    droids.add(Droid("B1"));
    droids.add(Droid("IT-O"));
    droids.add(Droid("BB-8"));
    droids.inorder();
    std::cout << std::endl;
    std::cout << "Root: " << droids.peekRoot() << std::endl;

    // search for a key that is in the tree
    const Droid& found = droids.search("R2-D2");
    std::cout << "Search R2-D2: found " << found.getName() << std::endl;

    // search for a key that is not in the tree
    try {
        droids.search("C-3PO");
        std::cout << "Search C-3PO: found" << std::endl;
    } catch (const BSTException& e) {
        std::cout << "Search C-3PO: " << e.what() << std::endl;
    }
    std::cout << std::endl;

    // ------------------------------------------------------------
    // People: the key is an integer ID; remove them one at a time
    // ------------------------------------------------------------
    std::cout << "=== People ===" << std::endl;
    people.add(Person(4, "Black Widow", "867-5309"));
    people.add(Person(5, "Captain America", "606-0842"));
    people.add(Person(3, "Iron Man", "777-9311"));
    people.add(Person(2, "Hulk", "489-4608"));
    people.add(Person(6, "Thor", "853-3937"));
    people.add(Person(1, "Ant-Man", "842-3089"));
    std::cout << "Root: " << people.peekRoot() << std::endl;
    people.inorder();
    std::cout << std::endl;

    const Person& who = people.search(2);
    std::cout << "Search 2: " << who.getName() << ", phone " << who.getPhone() << std::endl;
    std::cout << std::endl;

    const int removeOrder[] = {3, 1, 5, 4, 2, 6};
    for (int id : removeOrder) {
        people.remove(id);
        std::cout << "After removing " << id << ":" << std::endl;
        people.inorder();
        std::cout << std::endl;
    }
    std::cout << "Empty after removing everyone? "
              << (people.isEmpty() ? "true" : "false") << std::endl;

    // operations on an empty tree should report an error, not crash
    try {
        people.remove(4);
    } catch (const BSTException& e) {
        std::cout << "Remove 4 from empty tree: " << e.what() << std::endl;
    }
    try {
        people.peekRoot();
    } catch (const BSTException& e) {
        std::cout << "Peek at empty tree: " << e.what() << std::endl;
    }

    return 0;
}
