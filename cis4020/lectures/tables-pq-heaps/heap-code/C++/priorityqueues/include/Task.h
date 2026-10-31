#ifndef TASK_H
#define TASK_H

#include <iostream>
#include <string>

// a job waiting to be done; the key is its priority
class Task {
private:
    int priority;
    std::string description;

public:
    Task(int priority, const std::string& description)
        : priority(priority), description(description) {}

    int getKey() const {
        return priority;
    }

    void setKey(int newKey) {
        priority = newKey;
    }

    const std::string& getDescription() const {
        return description;
    }

    friend std::ostream& operator<<(std::ostream& out, const Task& task) {
        return out << task.description << " (priority " << task.priority << ")";
    }
};

#endif
