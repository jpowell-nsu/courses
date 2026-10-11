package items;

import bst.KeyedItem;

public class Droid extends KeyedItem<String> {
	
	private String name;
	
	public Droid(String name) {
		super(name);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null || !(o instanceof Droid)) {
			return false;
		} else if(o == this) {
			return true;
		} 
		Droid temp = (Droid) o;
		return (temp.name.equals(this.name));
	}
	
	// equals and hashCode must agree: two droids that are equal
	// must return the same hash code
	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String toString() {
		return this.name;
	}
}
