/**
 * Car
 *
 * Example 1 version: fields, a constructor, getters, a validated setter backed
 * by a private helper method, and one ordinary behavior method.
 *
 * @author  CIS 3300
 * @version 1.0
 */
public class Car {

    // fields (data members) -- these hold each Car object's state
    private String make;
    private String model;
    private int year;
    private int mileage;

    // constructor -- runs once, when an object is created with "new"
    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.mileage = 0;         // every new car starts at zero miles
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
    }
}
