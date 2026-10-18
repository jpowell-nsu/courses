package rental;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Example 4 starter: a vehicle rental agency. This file only reads fleet.csv and prints
 * each row. Together, we will design the classes that turn each row into an object, and
 * then finish the tasks listed in the main method.
 *
 * @version 1.0
 */
public class RentalAgency {

    public static void main(String[] args) throws IOException {
        // fleet.csv sits in the project folder, next to the src folder.
        List<String> lines = Files.readAllLines(Path.of("fleet.csv"));

        // Skip the header row.
        for (String line : lines.subList(1, lines.size())) {
            String[] parts = line.split(",");
            String type = parts[0];
            System.out.println(type + " -> " + String.join(" | ", parts));

            // TODO 1: Turn each row into an object of the right class, and store every
            //         vehicle in ONE ArrayList. What should the list's element type be?
        }

        // TODO 2: Print the whole fleet, one vehicle per line.

        // TODO 3: Quote every vehicle for a 3-day trip of 240 miles, then for a 7-day
        //         trip of 900 miles. Print the cheapest vehicle for each trip.

        // TODO 4: Print every truck that can carry at least 3000 pounds.

        // TODO 5: Print every car that seats at least 7 people.
    }
}
