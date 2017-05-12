package de.bbs.recipedatabase.dao.Implementation;

public class IngredientPerRecipe {
	
	//attributes
	private Recipe recipe;
	private double amount;
	private Unit unit;
	private Category category;
	private Ingredient ingredient;
	
	
	//getters and setters
	public Recipe getRecipe() {
		return this.recipe;
	}
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	public double getAmount() {
		return this.amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Unit getUnit() {
		return this.unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	public Category getCategory() {
		return this.category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Ingredient getIngredient() {
		return this.ingredient;
	}
	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}
	
	
	//constructors
	public IngredientPerRecipe() {
		this(new Recipe(), 43,54, new Unit(), new Category(), new Ingredient());
	}
	public IngredientPerRecipe(Recipe recipe, double amount, Unit unit, Category category, Ingredient ingredient) {
		this.setRecipe(recipe);
		this.setAmount(amount);
		this.setUnit(unit);
		this.setCategory(category);
		this.setIngredient(ingredient);
	}
	
	
	//standard methods
		//toString
	@Override
	public String toString() {
		return this.getClass().getSimpleName()	+ " recipe: " + this.getRecipe()
												+ " amount: " + this.getAmount()
												+ " unit: " + this.getUnit()
												+ " category: " + this.getCategory()
												+ " ingedient: " + this.getIngredient()
		;
	}
	
		//hashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((ingredient == null) ? 0 : ingredient.hashCode());
		result = prime * result + ((recipe == null) ? 0 : recipe.hashCode());
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
		if (!(obj instanceof IngredientPerRecipe)) {
			return false;
		}
		IngredientPerRecipe other = (IngredientPerRecipe) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount)) {
			return false;
		}
		if (category == null) {
			if (other.category != null) {
				return false;
			}
		} else if (!category.equals(other.category)) {
			return false;
		}
		if (ingredient == null) {
			if (other.ingredient != null) {
				return false;
			}
		} else if (!ingredient.equals(other.ingredient)) {
			return false;
		}
		if (recipe == null) {
			if (other.recipe != null) {
				return false;
			}
		} else if (!recipe.equals(other.recipe)) {
			return false;
		}
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
