package droids;

public class Droid {

    private String name;
    private int unitNumber;
	
    public Droid(String name, int unitNumber) {
        this.name = name;
        this.unitNumber = unitNumber;
    }
	
    public String getName() {
        return name;
    }
    
    public int getUnitNumber() {
    	return unitNumber;
    }
	
    @Override
    public String toString() {
        return (this.name + "-" + unitNumber);
    }
}
