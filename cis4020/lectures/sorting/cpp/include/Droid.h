#ifndef DROID_H
#define DROID_H

#include <cstddef>
#include <functional>
#include <ostream>
#include <string>

// ---------------------------------------------------------------------------
// A droid with an integer ID and a name.
//
// Two droids are the same droid when their IDs match. The name plays no part
// in equality, ordering, or hashing, so the comparison operators and the hash
// all use only the ID.
// ---------------------------------------------------------------------------
class Droid {
public:
	// Default constructor. C++ needs one so that an array of Droid objects
	// can be declared before its elements are filled in (Java uses null).
	Droid();
	Droid(int id, const std::string& name);

	int getId() const;
	const std::string& getName() const;
	void setName(const std::string& name);

	// Same idea as the Java toString method.
	std::string toString() const;

	// Ordering by ID, smallest first. These take the place of Java's
	// compareTo and equals methods. Comparing the IDs directly avoids the
	// overflow that subtracting two IDs could cause.
	bool operator<(const Droid& other) const;
	bool operator>(const Droid& other) const;
	bool operator<=(const Droid& other) const;
	bool operator>=(const Droid& other) const;
	bool operator==(const Droid& other) const;
	bool operator!=(const Droid& other) const;

private:
	// Not const on purpose. A const data member would delete the assignment
	// operator, and the sorts need to assign Droid objects when they swap.
	// The ID is never changed after construction, and no setter exists.
	int id;
	std::string name;
};

// Lets a Droid be printed with std::cout, using toString.
std::ostream& operator<<(std::ostream& out, const Droid& droid);

// Takes the place of Java's hashCode. Uses only the ID, so equal droids
// always produce equal hash codes. This allows Droid to be used as a key in
// std::unordered_set and std::unordered_map.
namespace std {
template <>
struct hash<Droid> {
	size_t operator()(const Droid& droid) const {
		return hash<int>()(droid.getId());
	}
};
}

#endif
