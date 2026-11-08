// HashTable.cpp
// Lab W14 starter code. See the lab handout for what each method must do.
#include "HashTable.h"

#include <cmath>
#include <cstdio>
#include <fstream>

HashTable::HashTable(int size) : table(size), count(0), deleted(0), lastProbes(0) {
    clear();
}

void HashTable::clear() {

}

int HashTable::h(int x) const {
    (void)x;  // remove this line when you write the method
    return 0;
}

int HashTable::h2(int x) const {
    (void)x;  // remove this line when you write the method
    return 0;
}

void HashTable::rehash(int newSize) {
    (void)newSize;  // remove this line when you write the method
}

int HashTable::nextPrime(int n) {

    return n;
}

bool HashTable::add(int x) {
    (void)x;  // remove this line when you write the method
    return false;
}

bool HashTable::remove(int x) {
    (void)x;  // remove this line when you write the method
    return false;
}

bool HashTable::find(int x) {
    (void)x;  // remove this line when you write the method
    return false;
}

int HashTable::probesFor(int x) {
    (void)x;  // remove this line when you write the method
    return 0;
}

int HashTable::getSize() const {
    return static_cast<int>(table.size());
}

double HashTable::loadFactor() const {

    return 0.0;
}

double HashTable::successful() const {

    return 0.0;
}

double HashTable::unsuccessful() const {

    return 0.0;
}

void HashTable::information() const {
    std::printf("Table Size  : %5d\n", getSize());
    std::printf("Count       : %5d\n", count);
    std::printf("Deleted     : %5d\n", deleted);
    std::printf("Load Factor : %5.2f\n", loadFactor());
    std::printf("Successful  : %5.2f\n", successful());
    std::printf("Unsuccessful: %5.2f\n", unsuccessful());
}

void HashTable::dump(const std::string& filename) const {
    std::ofstream out(filename);
    char line[64];
    for (size_t i = 0; i < table.size(); i++) {
        if (table[i] == EMPTY) {
            std::snprintf(line, sizeof line, "Slot %5zu: empty\n", i);
        } else if (table[i] == DELETED) {
            std::snprintf(line, sizeof line, "Slot %5zu: deleted\n", i);
        } else {
            std::snprintf(line, sizeof line, "Slot %5zu: %d\n", i, table[i]);
        }
        out << line;
    }
}
