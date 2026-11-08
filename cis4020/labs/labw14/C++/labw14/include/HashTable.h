// HashTable.h
// Open-addressing hash table of nonnegative integer keys, using double hashing.
// Lab W14 starter code. See the lab handout for what each method must do.
#ifndef HASHTABLE_H
#define HASHTABLE_H

#include <string>
#include <vector>

class HashTable {
public:
    explicit HashTable(int size);

    void clear();
    bool add(int x);
    bool remove(int x);
    bool find(int x);
    int probesFor(int x);
    int getSize() const;
    double loadFactor() const;
    double successful() const;
    double unsuccessful() const;
    void information() const;
    void dump(const std::string& filename) const;

private:
    static constexpr int EMPTY = -1;      // slot has never held a key
    static constexpr int DELETED = -2;    // slot held a key that was removed
    static constexpr double MAX_LOAD = 2.0 / 3.0;  // grow when (keys + deleted) / size would pass this

    std::vector<int> table;  // the slots
    int count;               // number of keys in the table
    int deleted;             // number of DELETED markers in the table
    int lastProbes;          // probes used by the most recent search

    int h(int x) const;
    int h2(int x) const;
    void rehash(int newSize);
    static int nextPrime(int n);
    // You may add private helper methods here.
};

#endif
