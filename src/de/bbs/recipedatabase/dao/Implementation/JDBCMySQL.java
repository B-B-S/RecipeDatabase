package de.bbs.recipedatabase.dao.Implementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.Vector;

import de.bbs.recipedatabase.dao.AJDBCImplementsIDAO;
import de.bbs.recipedatabase.exceptions.SQLConnectionNullException;
import de.bbs.recipedatabase.exceptions.SQLStatementNullException;

public class JDBCMySQL extends AJDBCImplementsIDAO {
	
	//attributes
	private static JDBCMySQL dAOInstance = null;
	
	
	//getters and setters
		//implement singleton
	public static JDBCMySQL getDAOInstance() {
		if(dAOInstance == null) {
			dAOInstance = new JDBCMySQL();
		}
		
		return dAOInstance;
	}
	
	
	//constructors
	private JDBCMySQL() {
		super("jdbc:mysql://");
	}
	
	
	
	
	//features
		//Style
	public Vector<Style> retrieveStyles() throws SQLException,SQLStatementNullException {
		ResultSet retrievedResult = super.performExecuteQuery(
										"SELECT * " +
										"FROM Style " +
										";"
									)
		;
		
		//initialize return Vector
		Vector<Style> retrievedStyles = new Vector<>();
		
		//retrieve all available styles and add them to the return vector
		while(retrievedResult.next()) {
			retrievedStyles.add(new Style(retrievedResult.getString("name")));
		}
		
		//return retrieved styles
		return retrievedStyles;
	}
	
	public int insertStyle(Style style) throws SQLException,SQLStatementNullException,SQLConnectionNullException {
		//check whether the style to be added is already present in the database
		boolean present  =	super.performExecuteQuery(
								"SELECT * " +
								"FROM Style " +
								"WHERE " +  "name = " + "\"" + style.getName() + "\" " + 
								";"
							).first()
		;
		
		//initialize return variable (will show whether a row will have been inserted by the end of method invocation)
		int rowAffected = 0;
		
		//add style to database if not already present and assign return value
		if(!present) {
			rowAffected =	super.performExecuteUpdate(
								"INSERT INTO Style " +
								"(name) " +
								"VALUES(" + "\"" + style.getName() + "\"" + ") " +
								";"
							)
			;
		}
		
		//return whether a row has been added
		return rowAffected;
	}
	
	public int removeStyles(Style... styles) throws SQLException,SQLStatementNullException,SQLConnectionNullException{
		//build filter StringBuffer for records/styles that are requested to be removed
		StringBuffer removeRequest = new StringBuffer("");
		for(int i = 0; i < styles.length; i++) {
			if(i > 0) {
				removeRequest.append(",");
			}
			removeRequest.append("\"");
			removeRequest.append(styles[i].getName());
			removeRequest.append("\"");
		}
		
		//retrieve all records that will be removed (not being referenced by other tables and requested to be removed)
		ResultSet removeResult =	super.performExecuteQuery(
										"SELECT name " +
										"FROM Style " +
										"WHERE NOT EXISTS ( " +
											"SELECT * " +
											"FROM Recipe " +
											"WHERE Style.name = Recipe.name_style " +
										") " +
										"AND Style.name IN( " +
											removeRequest +
										") " +
										";"
									)
		;
		
		//initialize return variable that will how many rows will have been removed
		int rowsAffected = 0;
		
		//remove records
			//generate StringBuffer for records to be removed
		StringBuffer removeStringBuffer = new StringBuffer("");
		while(removeResult.next()) {
			removeStringBuffer.append("\"");
			removeStringBuffer.append(removeResult.getString("name"));
			removeStringBuffer.append("\",");
		}
			//if there is anything to remove, remove it
		if(removeStringBuffer.length() > 0) {
			//remove last comma
			removeStringBuffer.deleteCharAt(removeStringBuffer.length()-1);
			//perform removal operation and assign return value
			rowsAffected =	super.performExecuteUpdate(
								"DELETE FROM Style " +
								"WHERE " +
								"name IN(" + removeStringBuffer + ")" +
								";"
							)
			;
		}
		
		//return how many rows have been deleted
		return rowsAffected;
	}
	
	
		//Unit
	public Vector<Unit> retrieveUnits() throws SQLException,SQLStatementNullException {
		ResultSet retrievedResult = super.performExecuteQuery(
										"SELECT * " +
										"FROM Unit " +
										";"
									)
		;

		//initialize return Vector
		Vector<Unit> retrievedUnits = new Vector<>();

		//retrieve all available units and add them to the return vector
		while(retrievedResult.next()) {
			retrievedUnits.add(new Unit(retrievedResult.getString("unit")));
		}

		//return retrieved units
		return retrievedUnits;
	}
	
	public int insertUnit(Unit unit) throws SQLException,SQLStatementNullException,SQLConnectionNullException {
		//check whether the unit to be added is already present in the database
		boolean present  =	super.performExecuteQuery(
								"SELECT * " +
								"FROM Unit " +
								"WHERE " +  "unit = " + "\"" + unit.getUnit() + "\" " + 
								";"
							).first()
		;
		
		//initialize return variable (will show whether a row will have been inserted by the end of method invocation)
		int rowAffected = 0;
		
		//add unit to database if not already present and assign return value
		if(!present) {
			rowAffected =	super.performExecuteUpdate(
								"INSERT INTO Unit " +
								"(unit) " +
								"VALUES(" + "\"" + unit.getUnit() + "\"" + ") " +
								";"
							)
			;
		}
	
		//return whether a row has been added
		return rowAffected;
	}
	
	public int removeUnits(Unit... units) throws SQLException,SQLStatementNullException,SQLConnectionNullException {
		//build filter StringBuffer for records/units that are requested to be removed
		StringBuffer removeRequest = new StringBuffer("");
		for(int i = 0; i < units.length; i++) {
			if(i > 0) {
				removeRequest.append(",");
			}
			removeRequest.append("\"");
			removeRequest.append(units[i].getUnit());
			removeRequest.append("\"");
		}
		
		//retrieve all records that will be removed (not being referenced by other tables and requested to be removed)
		ResultSet removeResult =	super.performExecuteQuery(
										"SELECT unit " +
										"FROM Unit " +
										"WHERE NOT EXISTS ( " +
											"SELECT * " +
											"FROM IngredientPerRecipe " +
											"WHERE Unit.unit = IngredientPerRecipe.unit_unit " +
										") " +
										"AND Unit.unit IN( " +
											removeRequest +
										") " +
										";"
									)
		;
		
		//initialize return variable that will show how many rows will have been removed
		int rowsAffected = 0;
		
		//remove records
			//generate StringBuffer for records to be removed
		StringBuffer removeStringBuffer = new StringBuffer("");
		while(removeResult.next()) {
			removeStringBuffer.append("\"");
			removeStringBuffer.append(removeResult.getString("unit"));
			removeStringBuffer.append("\",");
		}
			//if there is anything to remove, remove it
		if(removeStringBuffer.length() > 0) {
			//remove last comma
			removeStringBuffer.deleteCharAt(removeStringBuffer.length()-1);
			//perform removal operation and assign return value
			rowsAffected =	super.performExecuteUpdate(
								"DELETE FROM Unit " +
								"WHERE " +
								"unit IN(" + removeStringBuffer + ")" +
								";"
							)
			;
		}
		
		//return how many rows have been deleted
		return rowsAffected;
	}
	
	
		//Category
	public Vector<Category> retrieveCategories() throws SQLException,SQLStatementNullException {
		ResultSet retrievedResult = super.performExecuteQuery(
										"SELECT * " +
										"FROM Category " +
										";"
									)
		;

		//initialize return Vector
		Vector<Category> retrievedCategories = new Vector<>();

		//retrieve all available categories and add them to the return vector
		while(retrievedResult.next()) {
			retrievedCategories.add(new Category(retrievedResult.getString("category")));
		}

		//return retrieved categories
		return retrievedCategories;
	}
	
	public int insertCategory(Category category) throws SQLException,SQLStatementNullException,SQLConnectionNullException {
		//check whether the category to be added is already present in the database
		boolean present  =	super.performExecuteQuery(
								"SELECT * " +
								"FROM Category " +
								"WHERE " +  "category = " + "\"" + category.getCategory() + "\" " + 
								";"
							).first()
		;
		
		//initialize return variable (will show whether a row will have been inserted by the end of method invocation)
		int rowAffected = 0;
		
		//add category to database if not already present and assign return value
		if(!present) {
			rowAffected =	super.performExecuteUpdate(
								"INSERT INTO Category " +
								"(category) " +
								"VALUES(" + "\"" + category.getCategory() + "\"" + ") " +
								";"
							)
			;
		}
		
		//return whether a row has been added
		return rowAffected;
	}
	
	public int removeCategories(Category... categories) throws SQLException,SQLStatementNullException,SQLConnectionNullException {
		//build filter StringBuffer for records/categories that are requested to be removed
		StringBuffer removeRequest = new StringBuffer("");
		for(int i = 0; i < categories.length; i++) {
			if(i > 0) {
				removeRequest.append(",");
			}
			removeRequest.append("\"");
			removeRequest.append(categories[i].getCategory());
			removeRequest.append("\"");
		}
		
		//retrieve all records that will be removed (not being referenced by other tables and requested to be removed)
		ResultSet removeResult =	super.performExecuteQuery(
										"SELECT category " +
										"FROM Category " +
										"WHERE NOT EXISTS ( " +
											"SELECT * " +
											"FROM IngredientPerRecipe " +
											"WHERE Category.category = IngredientPerRecipe.category_category " +
										") " +
										"AND Category.category IN( " +
											removeRequest +
										") " +
										";"
									)
		;
		
		//initialize return variable that will show how many rows will have been removed
		int rowsAffected = 0;
		
		//remove records
			//generate StringBuffer for records to be removed
		StringBuffer removeStringBuffer = new StringBuffer("");
		while(removeResult.next()) {
			removeStringBuffer.append("\"");
			removeStringBuffer.append(removeResult.getString("category"));
			removeStringBuffer.append("\",");
		}
			//if there is anything to remove, remove it
		if(removeStringBuffer.length() > 0) {
			//remove last comma
			removeStringBuffer.deleteCharAt(removeStringBuffer.length()-1);
			//perform removal operation and assign return value
			rowsAffected =	super.performExecuteUpdate(
								"DELETE FROM Category " +
								"WHERE " +
								"category IN(" + removeStringBuffer + ")" +
								";"
							)
			;
		}
		
		//return how many rows have been deleted
		return rowsAffected;
	}
	
	
		//Packaging
	public Vector<Packaging> retrievePackagings() throws SQLException,SQLStatementNullException {
		ResultSet retrievedResult = super.performExecuteQuery(
										"SELECT * " +
										"FROM Packaging " +
										";"
								)
		;

		//initialize return Vector
		Vector<Packaging> retrievedPackagings = new Vector<>();

		//retrieve all available packagings and add them to the return vector
		while(retrievedResult.next()) {
			retrievedPackagings.add(new Packaging(retrievedResult.getString("packaging")));
		}

		//return retrieved packagings
		return retrievedPackagings;
	}
	
	public int insertPackaging(Packaging packaging) throws SQLException,SQLStatementNullException,SQLConnectionNullException {
		//check whether the packaging to be added is already present in the database
		boolean present  =	super.performExecuteQuery(
								"SELECT * " +
								"FROM Packaging " +
								"WHERE " +  "packaging = " + "\"" + packaging.getPackaging() + "\" " + 
								";"
							).first()
		;
		
		//initialize return variable (will show whether a row will have been inserted by the end of method invocation)
		int rowAffected = 0;
		
		//add packaging to database if not already present and assign return value
		if(!present) {
			rowAffected =	super.performExecuteUpdate(
								"INSERT INTO Packaging " +
								"(packaging) " +
								"VALUES(" + "\"" + packaging.getPackaging() + "\"" + ") " +
								";"
							)
			;
		}
		
		//return whether a row has been added
		return rowAffected;
	}
	
	public int removePackagings(Packaging... packagings) throws SQLException,SQLStatementNullException,SQLConnectionNullException {
		//build filter StringBuffer for records/packagings that are requested to be removed
		StringBuffer removeRequest = new StringBuffer("");
		for(int i = 0; i < packagings.length; i++) {
			if(i > 0) {
				removeRequest.append(",");
			}
			removeRequest.append("\"");
			removeRequest.append(packagings[i].getPackaging());
			removeRequest.append("\"");
		}
		
		//retrieve all records that will be removed (not being referenced by other tables and requested to be removed)
		ResultSet removeResult =	super.performExecuteQuery(
										"SELECT packaging " +
										"FROM Packaging " +
										"WHERE NOT EXISTS ( " +
											"SELECT * " +
											"FROM Ingredient " +
											"WHERE Packaging.packaging = Ingredient.packaging_packaging " +
										") " +
										"AND Packaging.packaging IN( " +
											removeRequest +
										") " +
										";"
									)
		;
		
		//initialize return variable that will show how many rows will have been removed
		int rowsAffected = 0;
		
		//remove records
			//generate StringBuffer for records to be removed
		StringBuffer removeStringBuffer = new StringBuffer("");
		while(removeResult.next()) {
			removeStringBuffer.append("\"");
			removeStringBuffer.append(removeResult.getString("packaging"));
			removeStringBuffer.append("\",");
		}
			//if there is anything to remove, remove it
		if(removeStringBuffer.length() > 0) {
			//remove last comma
			removeStringBuffer.deleteCharAt(removeStringBuffer.length()-1);
			//perform removal operation and assign return value
			rowsAffected =	super.performExecuteUpdate(
								"DELETE FROM Packaging " +
								"WHERE " +
								"packaging IN(" + removeStringBuffer + ")" +
								";"
							)
			;
		}
		
		//return how many rows have been deleted
		return rowsAffected;
	}
	
	
		//Form
	public Vector<Form> retrieveForms() throws SQLException,SQLStatementNullException {
		ResultSet retrievedResult = super.performExecuteQuery(
										"SELECT * " +
										"FROM Form " +
										";"
									)
		;

		//initialize return Vector
		Vector<Form> retrievedForms = new Vector<>();

		//retrieve all available forms and add them to the return vector
		while(retrievedResult.next()) {
			retrievedForms.add(new Form(retrievedResult.getString("form")));
		}

		//return retrieved forms
		return retrievedForms;
	}
	
	public int insertForm(Form form) throws SQLException,SQLStatementNullException,SQLConnectionNullException {
		//check whether the form to be added is already present in the database
		boolean present  =	super.performExecuteQuery(
								"SELECT * " +
								"FROM Form " +
								"WHERE " +  "form = " + "\"" + form.getForm() + "\" " + 
								";"
							).first()
		;
		
		//initialize return variable (will show whether a row will have been inserted by the end of method invocation)
		int rowAffected = 0;
		
		//add form to database if not already present and assign return value
		if(!present) {
			rowAffected =	super.performExecuteUpdate(
								"INSERT INTO Form " +
								"(form) " +
								"VALUES(" + "\"" + form.getForm() + "\"" + ") " +
								";"
							)
			;
		}
		
		//return whether a row has been added
		return rowAffected;
	}
	
	public int removeForms(Form... forms) throws SQLException,SQLStatementNullException,SQLConnectionNullException {
		//build filter StringBuffer for records/forms that are requested to be removed
		StringBuffer removeRequest = new StringBuffer("");
		for(int i = 0; i < forms.length; i++) {
			if(i > 0) {
				removeRequest.append(",");
			}
			removeRequest.append("\"");
			removeRequest.append(forms[i].getForm());
			removeRequest.append("\"");
		}
		
		//retrieve all records that will be removed (not being referenced by other tables and requested to be removed)
		ResultSet removeResult =	super.performExecuteQuery(
										"SELECT form " +
										"FROM Form " +
										"WHERE NOT EXISTS ( " +
											"SELECT * " +
											"FROM Ingredient " +
											"WHERE Form.form = Ingredient.form_form " +
										") " +
										"AND Form.form IN( " +
											removeRequest +
										") " +
										";"
									)
		;
		
		//initialize return variable that will show how many rows will have been removed
		int rowsAffected = 0;
		
		//remove records
			//generate StringBuffer for records to be removed
		StringBuffer removeStringBuffer = new StringBuffer("");
		while(removeResult.next()) {
			removeStringBuffer.append("\"");
			removeStringBuffer.append(removeResult.getString("form"));
			removeStringBuffer.append("\",");
		}
			//if there is anything to remove, remove it
		if(removeStringBuffer.length() > 0) {
			//remove last comma
			removeStringBuffer.deleteCharAt(removeStringBuffer.length()-1);
			//perform removal operation and assign return value
			rowsAffected =	super.performExecuteUpdate(
								"DELETE FROM Form " +
								"WHERE " +
								"form IN(" + removeStringBuffer + ")" +
								";"
							)
			;
		}
		
		//return how many rows have been deleted
		return rowsAffected;
	}
	
	
		//Spicy
	public Vector<Spicy> retrieveSpicies() throws SQLException,SQLStatementNullException {
		ResultSet retrievedResult = super.performExecuteQuery(
										"SELECT * " +
										"FROM Spicy " +
										";"
									)
		;

		//initialize return Vector
		Vector<Spicy> retrievedSpicies = new Vector<>();

		//retrieve all available spicies and add them to the return vector
		while(retrievedResult.next()) {
			retrievedSpicies.add(new Spicy(retrievedResult.getString("spicy")));
		}

		//return retrieved spicies
		return retrievedSpicies;
	}
	
	public int insertSpicy(Spicy spicy) throws SQLException,SQLStatementNullException,SQLConnectionNullException {
		//check whether the spicy to be added is already present in the database
		boolean present  =	super.performExecuteQuery(
								"SELECT * " +
								"FROM Spicy " +
								"WHERE " +  "spicy = " + "\"" + spicy.getSpicy() + "\" " + 
								";"
							).first()
		;
		
		//initialize return variable (will show whether a row will have been inserted by the end of method invocation)
		int rowAffected = 0;
		
		//add spicy to database if not already present and assign return value
		if(!present) {
			rowAffected =	super.performExecuteUpdate(
								"INSERT INTO Spicy " +
								"(spicy) " +
								"VALUES(" + "\"" + spicy.getSpicy() + "\"" + ") " +
								";"
							)
			;
		}
		
		//return whether a row has been added
		return rowAffected;
	}
	
	public int removeSpicies(Spicy... spicies) throws SQLException,SQLStatementNullException,SQLConnectionNullException {
		//build filter StringBuffer for records/spicies that are requested to be removed
		StringBuffer removeRequest = new StringBuffer("");
		for(int i = 0; i < spicies.length; i++) {
			if(i > 0) {
				removeRequest.append(",");
			}
			removeRequest.append("\"");
			removeRequest.append(spicies[i].getSpicy());
			removeRequest.append("\"");
		}
		
		//retrieve all records that will be removed (not being referenced by other tables and requested to be removed)
		ResultSet removeResult =	super.performExecuteQuery(
										"SELECT spicy " +
										"FROM Spicy " +
										"WHERE NOT EXISTS ( " +
											"SELECT * " +
											"FROM Ingredient " +
											"WHERE Spicy.spicy = Ingredient.spicy_spicy " +
										") " +
										"AND Spicy.spicy IN( " +
											removeRequest +
										") " +
										";"
									)
		;
		
		//initialize return variable that will show how many rows will have been removed
		int rowsAffected = 0;
		
		//remove records
			//generate StringBuffer for records to be removed
		StringBuffer removeStringBuffer = new StringBuffer("");
		while(removeResult.next()) {
			removeStringBuffer.append("\"");
			removeStringBuffer.append(removeResult.getString("spicy"));
			removeStringBuffer.append("\",");
		}
			//if there is anything to remove, remove it
		if(removeStringBuffer.length() > 0) {
			//remove last comma
			removeStringBuffer.deleteCharAt(removeStringBuffer.length()-1);
			//perform removal operation and assign return value
			rowsAffected =	super.performExecuteUpdate(
								"DELETE FROM Spicy " +
								"WHERE " +
								"spicy IN(" + removeStringBuffer + ")" +
								";"
							)
			;
		}
		
		//return how many rows have been deleted
		return rowsAffected;
	}
}
