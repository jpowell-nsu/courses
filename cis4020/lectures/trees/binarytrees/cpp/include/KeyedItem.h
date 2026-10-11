// KeyedItem.h -- base class for anything stored in the BST
// (C++ counterpart of KeyedItem.java). The key has no setter: if the
// key of an item already in the tree could change, the item could end
// up on the wrong side of its parent.
#ifndef KEYEDITEM_H
#define KEYEDITEM_H

template <typename KeyType>
class KeyedItem {
private:
    KeyType key;

public:
    explicit KeyedItem(const KeyType& key) : key(key) {}
    virtual ~KeyedItem() = default;

    const KeyType& getKey() const { return key; }
};

#endif
