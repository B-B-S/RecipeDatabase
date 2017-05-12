package de.bbs.recipedatabase.dao.Implementation;

public class Ingredient {

	//attributes
	private String name;
	private Packaging packaging;
	private Spicy spicy;
	private long kcal;
	private Form form;
	
	
	//getters and setters
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Packaging getPackaging() {
		return this.packaging;
	}
	public void setPackaging(Packaging packaging) {
		this.packaging = packaging;
	}
	public Spicy getSpicy() {
		return this.spicy;
	}
	public void setSpicy(Spicy spicy) {
		this.spicy = spicy;
	}
	public long getKcal() {
		return this.kcal;
	}
	public void setKcal(long kcal) {
		this.kcal = kcal;
	}
	public Form getForm() {
		return this.form;
	}
	public void setForm(Form form) {
		this.form = form;
	}

	
	//constructors
	public Ingredient() {
		this("ground pepper", new Packaging(), new Spicy(), 450, new Form());
	}
	public Ingredient(String name, Packaging packaging, Spicy spicy, long kcal, Form form) {
		this.setName(name);
		this.setPackaging(packaging);
		this.setSpicy(spicy);
		this.setKcal(kcal);
		this.setForm(form);
	}
	
	
	//standard methods
		//toString
	@Override
	public String toString() {
		return this.getClass().getSimpleName()	+ " name: " + this.getName()
												+ " packaging: " + this.getPackaging()
												+ " spicy: " + this.getSpicy()
												+ " kcal: " + this.getKcal()
												+ " form: " + this.getForm()
		;
	}
	
		//hashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((form == null) ? 0 : form.hashCode());
		result = prime * result + (int) (kcal ^ (kcal >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((packaging == null) ? 0 : packaging.hashCode());
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
		if (!(obj instanceof Ingredient)) {
			return false;
		}
		Ingredient other = (Ingredient) obj;
		if (form == null) {
			if (other.form != null) {
				return false;
			}
		} else if (!form.equals(other.form)) {
			return false;
		}
		if (kcal != other.kcal) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (packaging == null) {
			if (other.packaging != null) {
				return false;
			}
		} else if (!packaging.equals(other.packaging)) {
			return false;
		}
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
