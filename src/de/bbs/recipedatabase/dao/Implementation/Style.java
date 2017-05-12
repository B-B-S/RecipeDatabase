package de.bbs.recipedatabase.dao.Implementation;

public class Style {
	
	//attributes
	private String name;

	
	//getters and setters
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
			this.name = name;
	}
	
	
	//constructors
	public Style() {
		this("italian");
	}
	public Style(String name) {
		this.setName(name);
	}
	
	
	//standard methods
		//toString
	@Override
	public String toString() {
		return this.getClass().getSimpleName()	+ " name: " + this.getName()
		;
	}
		//hashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (!(obj instanceof Style)) {
			return false;
		}
		Style other = (Style) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}
}
