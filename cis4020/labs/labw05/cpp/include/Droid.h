#pragma once

#include <ostream>
#include <string>

class Droid {
public:
	explicit Droid(const std::string& name) : name(name) {}

	const std::string& getName() const {
		return name;
	}

	// Plays the same role as Java's toString().
	friend std::ostream& operator<<(std::ostream& out, const Droid& droid) {
		return out << droid.name;
	}
	
	bool operator==(const Droid& other) const {
	    return name == other.name;
	}

    bool operator!=(const Droid& other) const {
        return !(*this == other);
    }

private:
	std::string name;
};
