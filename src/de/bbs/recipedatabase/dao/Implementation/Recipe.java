package de.bbs.recipedatabase.dao.Implementation;

public class Recipe {
	
	//attributes
	private String name;
	private String description;
	private String instructions;
	private Style style;
	private String picture;
	
	
	//getters and setters
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getInstructions() {
		return this.instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	public Style getStyle() {
		return this.style;
	}
	public void setStyle(Style style) {
		this.style = style;
	}
	public String getPicture() {
		return this.picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	
	//constructors
	public Recipe() {
		this("Spaghetti with tuna", "Nice little dish with some fish.", "Cook noodles and add tuna.", new Style(), "testimage.jpg");
	}
	public Recipe(String name, String description, String instructions, Style style, String picture) {
		this.setName(name);
		this.setDescription(description);
		this.setInstructions(instructions);
		this.setStyle(style);
		this.setPicture(picture);
	}
	
	
	//standard methods
		//toString
	@Override
	public String toString() {
		return this.getClass().getSimpleName()	+ " name: " + this.getName()
												+ " description: " + this.getDescription()
												+ " instructions: " + this.getInstructions()
												+ " style: " + this.getStyle()
												+ " picture: " + this.getPicture()
		;
	}
	
		//hashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((instructions == null) ? 0 : instructions.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((picture == null) ? 0 : picture.hashCode());
		result = prime * result + ((style == null) ? 0 : style.hashCode());
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
		if (!(obj instanceof Recipe)) {
			return false;
		}
		Recipe other = (Recipe) obj;
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (instructions == null) {
			if (other.instructions != null) {
				return false;
			}
		} else if (!instructions.equals(other.instructions)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (picture == null) {
			if (other.picture != null) {
				return false;
			}
		} else if (!picture.equals(other.picture)) {
			return false;
		}
		if (style == null) {
			if (other.style != null) {
				return false;
			}
		} else if (!style.equals(other.style)) {
			return false;
		}
		return true;
	}
}
