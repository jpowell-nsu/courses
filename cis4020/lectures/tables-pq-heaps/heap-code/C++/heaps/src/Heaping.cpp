#include <cctype>
#include <iostream>
#include <memory>
#include <string>
#include "HeapArrayBased.h"
#include "HeapReferenceBased.h"
#include "Item.h"

// prints the prompt and returns the first character typed, in lowercase
// (a blank line or the end of input counts as quit)
char readChoice(const std::string& prompt) {
    std::cout << prompt;
    std::string line;
    if (!std::getline(std::cin, line)) {
        return 'q';
    }
    std::size_t first = line.find_first_not_of(" \t");
    if (first == std::string::npos) {
        return 'q';
    }
    return static_cast<char>(std::tolower(static_cast<unsigned char>(line[first])));
}

// prints the prompt and reads a whole number
// throws std::invalid_argument if the line is not a number
int readInt(const std::string& prompt) {
    std::cout << prompt;
    std::string line;
    std::getline(std::cin, line);
    return std::stoi(line);
}

int main() {
    // choose the kind of heap and how it is stored
    char kind = readChoice("Select (m)axheap or mi(n)heap: ");
    HeapType type = (kind == 'n') ? HeapType::MIN : HeapType::MAX;
    char storage = readChoice("Select (a)rray-based or (r)eference-based: ");

    std::unique_ptr<HeapInterface<Item, int>> theHeap;
    if (storage == 'r') {
        theHeap = std::make_unique<HeapReferenceBased<Item, int>>(type);
    } else {
        theHeap = std::make_unique<HeapArrayBased<Item, int>>(31, type);  // max size 31
    }
    std::cout << "Using " << (storage == 'r' ? "a reference-based " : "an array-based ")
              << (type == HeapType::MIN ? "minheap" : "maxheap") << "\n";

    int start[] = {70, 40, 50, 20, 60, 100, 80, 30, 10, 90};
    for (int key : start) {  // insert 10 items
        theHeap->insert(Item(key));
    }
    theHeap->displayHeap();

    char choice = readChoice("Select (i)nsert (d)elete (p)eek (s)how (c)hange (q)uit: ");
    while (choice != 'q') {
        try {
            switch (choice) {
                case 's':  // display
                    theHeap->displayHeap();
                    break;
                case 'i': {  // insert
                    int value = readInt("Enter value: ");
                    theHeap->insert(Item(value));
                    theHeap->displayHeap();
                    break;
                }
                case 'd': {  // remove
                    Item removed = theHeap->remove();
                    std::cout << "Removed " << removed.getKey() << "\n";
                    theHeap->displayHeap();
                    break;
                }
                case 'p':  // peek
                    std::cout << "Root is " << theHeap->peek().getKey() << "\n";
                    break;
                case 'c': {  // change
                    int index = readInt("Enter index of item: ");
                    int newKey = readInt("Enter new key value: ");
                    theHeap->change(index, newKey);
                    theHeap->displayHeap();
                    break;
                }
                default:
                    std::cout << "Invalid entry\n";
            }
        } catch (const HeapException& e) {
            std::cout << e.what() << "\n";
        } catch (const std::exception&) {  // std::stoi could not read a number
            std::cout << "Please enter a whole number\n";
        }
        choice = readChoice("Select (i)nsert (d)elete (p)eek (s)how (c)hange (q)uit: ");
    }
    return 0;
}
