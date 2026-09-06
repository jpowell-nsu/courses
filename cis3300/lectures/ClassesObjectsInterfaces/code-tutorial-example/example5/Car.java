import java.util.Objects;

/**
 * Car
 *
 * Example 5 version: implements Serviceable, adding a real service() method
 * on top of everything built in Days 1 and 4.
 *
 * @author  CIS 3300
 * @version 1.2
 */
public class Car implements Serviceable {

    // fields (data members) -- these hold each Car object's state
    private String make;
    private String model;
    private int year;
    private int mileage;
    private int milesSinceService;

    // constructor -- runs once, when an object is created with "new"
    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.mileage = 0;              // every new car starts at zero miles
        this.milesSinceService = 0;
    }

    // getters -- public, read-only access to private fields
    public String getMake()  { return make; }
    public String getModel() { return model; }
    public int getYear()     { return year; }
    public int getMileage()  { return mileage; }

    // a setter, with its validation delegated to a private helper method
    public void setMileage(int newMileage) {
        if (isValidMileage(newMileage)) {
            mileage = newMileage;
        } else {
            System.out.println("Ignored an invalid mileage: " + newMileage);
        }
    }

    // a private helper method -- only code inside this class can call it
    private boolean isValidMileage(int candidate) {
        return candidate >= mileage;   // mileage should never run backward
    }

    // an ordinary behavior method, built on top of the setter above
    public void drive(int miles) {
        setMileage(mileage + miles);
        milesSinceService += miles;
    }

    @Override
    public String toString() {
        return year + " " + make + " " + model + " (" + mileage + " miles)";
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;                    // same object: trivially equal
        }
        if (!(other instanceof Car)) {
            return false;                   // not even a Car: cannot be equal
        }
        Car otherCar = (Car) other;
        return year == otherCar.year
            && make.equals(otherCar.make)
            && model.equals(otherCar.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, model, year);
    }

    @Override
    public void service() {
        if (milesSinceService >= MAX_MILES_BETWEEN_SERVICES) {
            System.out.println(model + " was overdue and has now been serviced.");
        } else {
            System.out.println(model + " has been serviced.");
        }
        milesSinceService = 0;   // resets the service interval, not the odometer
    }
}
