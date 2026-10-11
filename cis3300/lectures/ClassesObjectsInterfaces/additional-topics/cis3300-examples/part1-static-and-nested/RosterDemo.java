public class RosterDemo {
    public static void main(String[] args) {
        System.out.println("main starts");
        Roster cis = new Roster("CIS 3300");
        cis.add(new Student("Ada", 91.5));
        cis.add(new Student("Grace", 84.0));
        cis.add(new Student("Alan", 72.5));

        Roster math = new Roster("MATH 2100");
        math.add(new Student("Edsger", 77.0));
        System.out.println("Students created: " + Student.getStudentsCreated());

        cis.printReport();
        math.printReport();

        Roster.Stats stats = cis.computeStats();
        System.out.println(stats);

        System.out.println("Honor roll for CIS 3300:");
        cis.printIf(new StudentFilter() {
            @Override
            public boolean accept(Student s) {
                return s.getPercent() >= 90.0;
            }
        });

        System.out.println("Max size: " + Config.MAX_ROSTER_SIZE);
    }
}
