CIS 3300 Classes and Objects Review: Example Code
=================================================

Each folder holds the complete, compilable code for one set of notes. Several
folders contain a class named Student, and each one is a different version, so
keep each folder in its own Eclipse project (or its own directory on the
command line). All files use the default package, as the notes do.

To use a folder in Eclipse: create a new Java project, then copy that
folder's .java files into the project's src folder.

part1-static-and-nested
    Student, Config, Roster   The classes from Part 1.
    StudentFilter             The interface for the anonymous class example.
    RosterDemo                The "Putting It Together" program.
    StaticWayDemo             Accessing static members through an object.
                              It shows the "should be accessed in a static
                              way" warning ON PURPOSE. Do not copy that style
                              into submitted work.
    Note: Config reads an optional config.txt file. In Eclipse, place it in
    the project folder (not in src) to try the third "Try It" exercise.

part2-immutable-data-types/class-version
    Student                   The hand-written immutable class.
    ImmutableClassDemo        The String and withPercent examples.

part2-immutable-data-types/record-version
    Student, Section          The records.
    LetterGrade               The enum.
    RecordDemo                Records, enums, switch, and Comparable.
    RecordEqualsDemo          How record equals depends on its components:
                              arrays, classes without equals, mutable lists,
                              and double values.

part3-object-serialization
    Student, LetterGrade      From Part 2, with Student now Serializable.
    Gradebook                 The serializable gradebook.
    SaveGradebook             Run first; writes gradebook.ser.
    LoadGradebook             Reads gradebook.ser back.
    SafeLoadGradebook         Reads gradebook.ser with an ObjectInputFilter.
    SharedDemo                Shared references and enums after reading.
    TamperDemo                Edited bytes: the class accepts them, the
                              record rejects them.

Compiled and tested with Java 21. Every program prints the output shown in
the notes.
