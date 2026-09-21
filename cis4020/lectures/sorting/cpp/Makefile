# ---------------------------------------------------------------------------
# Makefile for the C++ sorting project
#
#   make        build the program (creates ./sorter)
#   make run    build if needed, then run the program
#   make clean  delete the build folder and the program
#
# Layout:
#   include/    header files (Droid.h, Sorts.h)
#   src/        source files (Droid.cpp, Sorter.cpp)
#   build/      object files, created automatically
# ---------------------------------------------------------------------------

CXX      := g++
CXXFLAGS := -std=c++17 -Wall -Wextra -pedantic -Iinclude

SRC_DIR  := src
OBJ_DIR  := build
TARGET   := sorter

# Every .cpp file in src/ is compiled, so new source files are picked up
# automatically. Each one becomes a matching .o file in build/.
SRCS := $(wildcard $(SRC_DIR)/*.cpp)
OBJS := $(patsubst $(SRC_DIR)/%.cpp,$(OBJ_DIR)/%.o,$(SRCS))
DEPS := $(OBJS:.o=.d)

all: $(TARGET)

# Link all of the object files into the finished program.
$(TARGET): $(OBJS)
	$(CXX) $(CXXFLAGS) -o $@ $^

# Compile one .cpp file into one .o file. The -MMD -MP flags also write a .d
# file that lists the headers each source file uses. That matters here because
# Sorts.h holds all of the sorting code: when it changes, Sorter.cpp must be
# rebuilt even though Sorter.cpp itself did not change.
$(OBJ_DIR)/%.o: $(SRC_DIR)/%.cpp | $(OBJ_DIR)
	$(CXX) $(CXXFLAGS) -MMD -MP -c $< -o $@

$(OBJ_DIR):
	mkdir -p $(OBJ_DIR)

run: $(TARGET)
	./$(TARGET)

clean:
	rm -rf $(OBJ_DIR) $(TARGET)

-include $(DEPS)

.PHONY: all run clean
