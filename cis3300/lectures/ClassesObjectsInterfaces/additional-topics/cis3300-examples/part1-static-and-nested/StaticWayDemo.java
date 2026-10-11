// Part 1, "Access static members in a static way."
// This file triggers the "should be accessed in a static way" warning on
// purpose, so you can see it in Eclipse. Do not submit code like the first
// three calls below; use the class name, as in the last three lines.
public class StaticWayDemo {
    public static void main(String[] args) {
        Student ada = new Student("Ada", 91.5);
        Student grace = new Student("Grace", 84.0);
        System.out.println(ada.getId() + " " + grace.getId());   // S1 S2

        // Non-static way: compiles and runs, but Eclipse warns.
        System.out.println(ada.getStudentsCreated());   // 2
        System.out.println(ada.ID_PREFIX);              // S

        // Proof that the object is ignored: no NullPointerException.
        Student nobody = null;
        System.out.println(nobody.getStudentsCreated());   // 2

        // Static way: no warning.
        System.out.println(Student.getStudentsCreated());   // 2
        System.out.println(Student.ID_PREFIX);              // S
    }
}
