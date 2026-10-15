package payroll;

/**
 * Someone who mentors newer employees.
 *
 * @version 1.0
 */
public interface Mentor {
    /** @return a description of the support this person gives */
    default String supportDescription() {
        return "meets with a mentee every week";
    }
}
