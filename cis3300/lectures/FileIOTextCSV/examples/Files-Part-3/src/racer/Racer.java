package racer;

public class Racer {
    // Fields: what every Racer object knows about itself.
    private String name;
    private String vehicle;
    private double topSpeed;

    // Constructor: fills in the fields from the values passed in.
    public Racer(String name, String vehicle, double topSpeed) {
        this.name = name;
        this.vehicle = vehicle;
        this.topSpeed = topSpeed;
    }

    // Getters: hand back one field at a time.
    public String getName() {
        return name;
    }

    public String getVehicle() {
        return vehicle;
    }

    public double getTopSpeed() {
        return topSpeed;
    }
}
