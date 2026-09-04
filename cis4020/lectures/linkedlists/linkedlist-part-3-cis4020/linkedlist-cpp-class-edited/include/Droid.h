#ifndef DROID_H
#define DROID_H

#include <iostream>
#include <string>

struct Droid {
    std::string model;
    int unitNumber;

    // Needed because LinkedList::display() and LinkedList::walk() print T
    // with std::cout << T. Without this, those two methods fail to compile
    // for LinkedList<Droid> specifically, everything else still works.
    friend std::ostream& operator<<(std::ostream& out, const Droid& d) {
        out << d.model << "-" << d.unitNumber;
        return out;
    }

    // Needed because LinkedList::remove() compares T values with ==.
    // Without this, remove() fails to compile for LinkedList<Droid>.
    bool operator==(const Droid& other) const {
        return model == other.model && unitNumber == other.unitNumber;
    }
};

#endif
