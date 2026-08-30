#include <iostream>
#include "LinkedList.h"
#include "Droid.h"

int main() {
    int values[] = {18, 10, 5, 100, 19, 3, 12};

    LinkedList<int> list;

    for (int i = 0; i < 7; i++) {
        list.addFirst(values[i]);
    }

    list.display();
    list.walk();

    LinkedList<Droid> droids;

    droids.addLast({"R2-D2", 1138});
    droids.addLast({"BB-8", 42});
    droids.addLast({"C-3PO", 66});
    droids.addLast({"R4-D4", 2187});

    droids.display();
    droids.walk();

    return 0;
}
