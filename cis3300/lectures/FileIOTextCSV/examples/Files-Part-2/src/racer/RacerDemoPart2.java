package racer;

import java.io.File;
import java.util.Scanner;

public class RacerDemoPart2 {
    public static void main(String[] args) throws Exception {
        Scanner fileScanner = new Scanner(new File("files/lapTimes.txt"));

        String fastestName = "";
        double fastestTime = Double.MAX_VALUE;

        // next() and nextDouble() automatically stop at whitespace, so
        // no manual splitting is needed for a space-separated file.
        while (fileScanner.hasNext()) {
            String name = fileScanner.next();
            double lapTime = fileScanner.nextDouble();

            System.out.println("Best lap for " + name + ": " + lapTime + " seconds");

            if (lapTime < fastestTime) {
                fastestTime = lapTime;
                fastestName = name;
            }
        }
        fileScanner.close();

        System.out.println("Fastest lap: " + fastestName);
    }
}
