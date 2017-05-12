package de.bbs.recipedatabase.dao.Implementation;

public class Form {

	//attributes
	private String form;
	
	
	//getters and setters
	public String getForm() {
		return this.form;
	}
	public void setForm(String form) {
		this.form = form;
	}
	
	
	//constructors
	public Form() {
		this("powder");
	}
	public Form(String form) {
		this.setForm(form);
	}
	
	
	//standard methods
		//toString
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " form: " + this.getForm()
		;
	}
	
		//hashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((form == null) ? 0 : form.hashCode());
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
		if (!(obj instanceof Form)) {
			return false;
		}
		Form other = (Form) obj;
		if (form == null) {
			if (other.form != null) {
				return false;
			}
		} else if (!form.equals(other.form)) {
			return false;
		}
		return true;
	}
}
