package droids;

import java.util.Objects;

public class Droid {

	private String name;
	
	public Droid(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return ("Name: " + this.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Droid other = (Droid) obj;
		return Objects.equals(name, other.name);
	}
	
}
