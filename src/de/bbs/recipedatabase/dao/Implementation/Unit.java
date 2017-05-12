package de.bbs.recipedatabase.dao.Implementation;

public class Unit {

	//attributes
	private String unit;
	
	
	//getters and setters
	public String getUnit() {
		return this.unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
	//constructors
	public Unit() {
		this("kg");
	}
	public Unit(String unit) {
		this.setUnit(unit);
	}
	
	
	//standard methods
		//toString
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " unit: " + this.getUnit()
		;
	}
	
		//hashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
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
		if (!(obj instanceof Unit)) {
			return false;
		}
		Unit other = (Unit) obj;
		if (unit == null) {
			if (other.unit != null) {
				return false;
			}
		} else if (!unit.equals(other.unit)) {
			return false;
		}
		return true;
	}
}
