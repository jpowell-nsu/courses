public enum LetterGrade {
    A(89.50, 4.0),
    B(79.50, 3.0),
    C(69.50, 2.0),
    D(59.50, 1.0),
    F(0.00, 0.0);

    private final double minPercent;
    private final double gradePoints;

    LetterGrade(double minPercent, double gradePoints) {
        this.minPercent = minPercent;
        this.gradePoints = gradePoints;
    }

    public double getMinPercent() { return minPercent; }
    public double getGradePoints() { return gradePoints; }

    // Relies on the constants being declared from highest cutoff to lowest.
    public static LetterGrade fromPercent(double percent) {
        for (LetterGrade g : values()) {
            if (percent >= g.minPercent) {
                return g;
            }
        }
        throw new IllegalArgumentException("percent below zero: " + percent);
    }
}
