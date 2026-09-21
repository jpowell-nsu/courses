package droids;

public class Droid implements Comparable<Droid> {

	private final int id;
	private String name;
	
	public Droid(int id, String name) {
		this.id = id;
		this.name = name;
	}
 
	public int getId() { return id; }
 	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
 
	/**
	 * Orders droids by ID, smallest first. Integer.compare is used instead of
	 * subtracting the two IDs because subtraction can overflow for very large
	 * or very negative values.
	 */
	@Override
	public int compareTo(Droid other) {
		return Integer.compare(this.id, other.id);
	}
 
	//Two droids are equal when their IDs are equal. The name is ignored.
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			// uses getClass() rather than instanceof, so a
			//	subclass instance is never equal to a plain Droid.
			return false;
		}
		Droid other = (Droid) obj;
		return this.id == other.id;
	}
 
	// Uses only the ID, so equal droids always produce equal hash codes.
	@Override
	public int hashCode() {
		return Integer.hashCode(id);
	}
 
	@Override
	public String toString() {
		return "Droid [id=" + id + ", name=" + name + "]";
	}

}
