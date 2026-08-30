JAVAC := javac
JAVA := java
SRC_DIR := src
BUILD_DIR := build
SRC := $(wildcard $(SRC_DIR)/*.java)
MAIN_CLASS := Main

.PHONY: all run clean

all: $(SRC)
	@mkdir -p $(BUILD_DIR)
	$(JAVAC) -d $(BUILD_DIR) $(SRC)

run: all
	$(JAVA) -cp $(BUILD_DIR) $(MAIN_CLASS)

clean:
	rm -rf $(BUILD_DIR)
