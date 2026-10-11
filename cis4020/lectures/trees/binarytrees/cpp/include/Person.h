// Person.h -- a person keyed by an integer ID
#ifndef PERSON_H
#define PERSON_H

#include <ostream>
#include <string>
#include "KeyedItem.h"

class Person : public KeyedItem<int> {
private:
    std::string name;
    std::string phone;

public:
    Person(int id, const std::string& name, const std::string& phone)
        : KeyedItem<int>(id), name(name), phone(phone) {}

    const std::string& getName() const { return name; }
    const std::string& getPhone() const { return phone; }

    friend std::ostream& operator<<(std::ostream& out, const Person& p) {
        return out << p.getKey() << " " << p.name << " " << p.phone;
    }
};

#endif
