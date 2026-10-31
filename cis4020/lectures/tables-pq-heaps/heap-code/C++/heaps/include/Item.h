#ifndef ITEM_H
#define ITEM_H

// the simplest item a heap can hold: just an integer key
class Item {
private:
    int key;

public:
    explicit Item(int key) : key(key) {}

    int getKey() const {
        return key;
    }

    void setKey(int newKey) {
        key = newKey;
    }
};

#endif
