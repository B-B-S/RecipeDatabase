package de.bbs.recipedatabase.dao.Implementation;

public class Packaging {
	
	//attributes
	private String packaging;
	
	
	//getters and setters
	public String getPackaging() {
		return this.packaging;
	}
	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}
	
	
	//constructors
	public Packaging() {
		this("tin can");
	}
	public Packaging(String packaging) {
		this.setPackaging(packaging);
	}
	
	
	//standard methods
		//toString
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " packaging: " + this.getPackaging()
		;
	}
	
		//hashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((packaging == null) ? 0 : packaging.hashCode());
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
		if (!(obj instanceof Packaging)) {
			return false;
		}
		Packaging other = (Packaging) obj;
		if (packaging == null) {
			if (other.packaging != null) {
				return false;
			}
		} else if (!packaging.equals(other.packaging)) {
			return false;
		}
		return true;
	}
}
