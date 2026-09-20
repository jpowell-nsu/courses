CXX := g++
CXXFLAGS := -std=c++17 -Wall -Wextra -Iinclude

SRC := src/main.cpp
TARGET := GarageDriver

.PHONY: all clean run

all: $(TARGET)

$(TARGET): $(SRC) include/Stack.h include/Queue.h
	$(CXX) $(CXXFLAGS) $(SRC) -o $(TARGET)

run: $(TARGET)
	./$(TARGET)

clean:
	rm -f $(TARGET):
