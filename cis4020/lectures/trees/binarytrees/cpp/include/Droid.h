// Droid.h -- a droid keyed by its name, so the tree orders droids
// alphabetically
#ifndef DROID_H
#define DROID_H

#include <ostream>
#include <string>
#include "KeyedItem.h"

class Droid : public KeyedItem<std::string> {
private:
    std::string name;

public:
    explicit Droid(const std::string& name) : KeyedItem<std::string>(name), name(name) {}

    const std::string& getName() const { return name; }

    // C++ counterpart of equals() in Java
    bool operator==(const Droid& other) const { return name == other.name; }

    friend std::ostream& operator<<(std::ostream& out, const Droid& d) {
        return out << d.name;
    }
};

#endif
