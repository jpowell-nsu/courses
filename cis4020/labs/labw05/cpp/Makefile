CXX := g++
CXXFLAGS := -std=c++17 -Wall -Wextra -Iinclude

SRC := src/main.cpp
TARGET := linkedlist_demo

.PHONY: all clean run

all: $(TARGET)

$(TARGET): $(SRC) include/LinkedList.h
	$(CXX) $(CXXFLAGS) $(SRC) -o $(TARGET)

run: $(TARGET)
	./$(TARGET)

clean:
	rm -f $(TARGET)
