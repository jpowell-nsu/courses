// employee.cpp
// A small C++ program built for disassembly practice.
//
// Suggested build (no optimization, so the assembly mirrors the source):
//     g++ -O0 -g -fno-inline -o employee employee.cpp
// Suggested ways to look at it:
//     objdump -d -C -M intel employee | less
//     gdb ./employee     (then: break main, run, disassemble, info registers)

#include <cstdio>

// ---------------------------------------------------------------------------
// Base class
// ---------------------------------------------------------------------------
class Person {
protected:
    char* name;   // pointer to a character array on the heap
    int   age;

public:
    // The constructor receives the character array as an argument and makes
    // its own heap copy, so the object owns its name.
    Person(const char* inName, int inAge) : name(nullptr), age(inAge) {
        int len = rawLength(inName);
        name = new char[len + 1];
        for (int i = 0; i <= len; i++) {   // <= copies the terminating '\0'
            name[i] = inName[i];
        }
    }

    // Virtual destructor, so deleting through a Person* runs ~Employee first.
    virtual ~Person() {
        delete[] name;
    }

    // Virtual method: overridden in Employee.
    virtual void describe() const {
        printf("Person: %s, age %d\n", name, age);
    }

    // Virtual method that uses a while loop.
    virtual int nameLength() const {
        int count = 0;
        while (name[count] != '\0') {
            count++;
        }
        return count;
    }

    const char* getName() const { return name; }

private:
    // Non-virtual helper: also a while loop, used before the object owns a name.
    static int rawLength(const char* s) {
        int n = 0;
        while (s[n] != '\0') {
            n++;
        }
        return n;
    }
};

// ---------------------------------------------------------------------------
// Derived class
// ---------------------------------------------------------------------------
class Employee : public Person {
private:
    int    employeeId;
    double hourlyRate;

public:
    Employee(const char* inName, int inAge, int inId, double inRate)
        : Person(inName, inAge), employeeId(inId), hourlyRate(inRate) {}

    ~Employee() override {
        printf("Destroying employee %d\n", employeeId);
    }

    // Override of a virtual method from Person.
    void describe() const override {
        printf("Employee #%d: %s, age %d, $%.2f per hour\n",
               employeeId, name, age, hourlyRate);
    }

    // Virtual method that uses a for loop: totals pay week by week,
    // adding a small raise every fourth week.
    virtual double payForWeeks(int weeks) const {
        double total = 0.0;
        double rate  = hourlyRate;
        for (int week = 1; week <= weeks; week++) {
            total += rate * 40.0;
            if (week % 4 == 0) {
                rate += 0.50;
            }
        }
        return total;
    }
};

// ---------------------------------------------------------------------------
// main
// ---------------------------------------------------------------------------
int main() {
    char nameBuffer[] = "Ada Lovelace";   // character array on the stack

    // The Employee object itself lives on the heap.
    Employee* emp = new Employee(nameBuffer, 36, 1001, 25.00);

    // Calling through a base-class pointer forces a dispatch through the vtable.
    Person* asPerson = emp;
    asPerson->describe();

    printf("Name length: %d\n", asPerson->nameLength());
    printf("Pay for 8 weeks: $%.2f\n", emp->payForWeeks(8));

    // Print the addresses so you can match them in gdb:
    // the object and its name are both heap addresses, nameBuffer is a stack address.
    printf("Employee object (heap):   %p\n", (void*)emp);
    printf("Name copy       (heap):   %p\n", (void*)emp->getName());
    printf("nameBuffer      (stack):  %p\n", (void*)nameBuffer);

    delete asPerson;   // virtual destructor: ~Employee then ~Person
    return 0;
}
