package pitlane;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Reference solution for the Pit Lane Log in-class challenge.
 *
 * Reads files/pitlog.txt one line at a time. A line with a comma in
 * it is a CHECKIN record (CHECKIN,name,kart,fuel); a line without one
 * is a LAP record (LAP name lapNumber lapTime). CHECKIN starts a new
 * driver with a running total lap time and lap count of zero; each
 * LAP adds to that driver's running total and count, found by name
 * with indexOf, the same search-or-add idea used in this week's lab.
 * Writes files/pitreport.txt with each driver's kart and average lap
 * time once every line has been read.
 *
 * This is one possible solution of many. We were working towards args
 * different solution in class. This one is nice because it also shows
 * more ArrayList features.
 */
public class PitLaneAnswerKey {
    public static void main(String[] args) throws Exception {
        ArrayList<String> driverNames = new ArrayList<String>();
        ArrayList<String> karts = new ArrayList<String>();
        ArrayList<Double> totalLapTime = new ArrayList<Double>();
        ArrayList<Integer> lapCount = new ArrayList<Integer>();

        Scanner fileScanner = new Scanner(new File("files/pitlog.txt"));

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();

            if (line.contains(",")) {
                // CHECKIN line: CHECKIN,name,kart,fuel
                String[] parts = line.split(",");
                String name = parts[1];
                String kart = parts[2];

                driverNames.add(name);
                karts.add(kart);
                totalLapTime.add(0.0);
                lapCount.add(0);
            } else {
                // LAP line: LAP name lapNumber lapTime
                Scanner lineScanner = new Scanner(line);
                lineScanner.next(); // the word LAP, not needed
                String name = lineScanner.next();
                lineScanner.nextInt(); // lap number, not needed
                double lapTime = lineScanner.nextDouble();
                lineScanner.close();

                int index = driverNames.indexOf(name);
                totalLapTime.set(index, totalLapTime.get(index) + lapTime);
                lapCount.set(index, lapCount.get(index) + 1);
            }
        }
        fileScanner.close();

        PrintWriter writer = new PrintWriter(new FileWriter("files/pitreport.txt"));
        for (int i = 0; i < driverNames.size(); i++) {
            double average = totalLapTime.get(i) / lapCount.get(i);
            writer.println(driverNames.get(i) + " (" + karts.get(i) + "): average lap "
                    + String.format("%.2f", average) + " seconds");
        }
        writer.close();
    }
}
