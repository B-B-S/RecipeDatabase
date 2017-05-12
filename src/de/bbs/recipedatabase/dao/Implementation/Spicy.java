package de.bbs.recipedatabase.dao.Implementation;

public class Spicy {

	//attributes
	private String spicy;
	
	
	//getters and setters
	public String getSpicy() {
		return this.spicy;
	}
	public void setSpicy(String spicy) {
		this.spicy = spicy;
	}
	
	
	//constructors
	public Spicy() {
		this("moderate");
	}
	public Spicy(String spicy) {
		this.setSpicy(spicy);
	}
	
	
	//standard methods
		//toString
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " spicy: " + this.getSpicy()
		;
	}
	
		//hashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((spicy == null) ? 0 : spicy.hashCode());
		return result;
	}
	
		//equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Spicy)) {
			return false;
		}
		Spicy other = (Spicy) obj;
		if (spicy == null) {
			if (other.spicy != null) {
				return false;
			}
		} else if (!spicy.equals(other.spicy)) {
			return false;
		}
		return true;
	}
}
