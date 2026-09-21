#include "Droid.h"

Droid::Droid() : id(0), name("") {}

Droid::Droid(int id, const std::string& name) : id(id), name(name) {}

int Droid::getId() const { return id; }

const std::string& Droid::getName() const { return name; }

void Droid::setName(const std::string& name) { this->name = name; }

std::string Droid::toString() const {
	return "Droid [id=" + std::to_string(id) + ", name=" + name + "]";
}

// All of the comparisons look only at the ID. The name is ignored.
bool Droid::operator<(const Droid& other) const { return id < other.id; }

bool Droid::operator>(const Droid& other) const { return other.id < id; }

bool Droid::operator<=(const Droid& other) const { return !(other.id < id); }

bool Droid::operator>=(const Droid& other) const { return !(id < other.id); }

bool Droid::operator==(const Droid& other) const { return id == other.id; }

bool Droid::operator!=(const Droid& other) const { return !(id == other.id); }

std::ostream& operator<<(std::ostream& out, const Droid& droid) {
	return out << droid.toString();
}
