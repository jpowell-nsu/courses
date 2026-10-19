package payroll;

/**
 * Someone who runs training sessions.
 *
 * @version 1.0
 */
public interface Trainer {
    /** @return a description of the support this person gives */
    default String supportDescription() {
        return "runs a training session every month";
    }
}
