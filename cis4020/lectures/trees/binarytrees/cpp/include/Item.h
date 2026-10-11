// Item.h -- holds an integer, keyed by an integer
#ifndef ITEM_H
#define ITEM_H

#include <ostream>
#include "KeyedItem.h"

class Item : public KeyedItem<int> {
private:
    // If the data and key are set to the same value, be careful not to
    // change the data without also changing the key. Changing the key
    // could move the item's place in the tree; in a BST, that is a
    // remove followed by an add.
    int data;

public:
    // must pass a key to the base class constructor
    Item(int key, int data) : KeyedItem<int>(key), data(data) {}

    int get() const { return data; }

    friend std::ostream& operator<<(std::ostream& out, const Item& item) {
        return out << item.data;
    }
};

#endif
