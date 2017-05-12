package de.bbs.recipedatabase.interfaces;

import java.sql.SQLException;
import java.util.Vector;

import de.bbs.recipedatabase.dao.Implementation.Category;
import de.bbs.recipedatabase.dao.Implementation.Form;
import de.bbs.recipedatabase.dao.Implementation.Packaging;
import de.bbs.recipedatabase.dao.Implementation.Spicy;
import de.bbs.recipedatabase.dao.Implementation.Style;
import de.bbs.recipedatabase.dao.Implementation.Unit;
import de.bbs.recipedatabase.exceptions.SQLConnectionNullException;
import de.bbs.recipedatabase.exceptions.SQLStatementNullException;

public interface IDAO {
	
	//getters and setters
		//define connection variable methods
	String getUser();
	void setUser(String user);
	String getPassword();
	void setPassword(String password);
	String getDataSourcePath();
	void setDataSourcePath(String dataSourcePath);
	
		
	//open, close methods
	void open() throws SQLException;
	void close() throws SQLException,SQLStatementNullException,SQLConnectionNullException;
	
	
	//features
		//list all styles
	Vector<Style> retrieveStyles() throws SQLException,SQLStatementNullException;
		//create or modify style
	int insertStyle(Style style) throws SQLException,SQLStatementNullException,SQLConnectionNullException;
	int removeStyles(Style... styles) throws SQLException,SQLStatementNullException,SQLConnectionNullException;
	
	
		//list all units
	Vector<Unit> retrieveUnits() throws SQLException,SQLStatementNullException;
		//create or modify unit
	int insertUnit(Unit unit) throws SQLException,SQLStatementNullException,SQLConnectionNullException;
	int removeUnits(Unit... units) throws SQLException,SQLStatementNullException,SQLConnectionNullException;
	
	
		//list all categories
	Vector<Category> retrieveCategories() throws SQLException,SQLStatementNullException;
		//create or modify categories
	int insertCategory(Category category) throws SQLException,SQLStatementNullException,SQLConnectionNullException;
	int removeCategories(Category... categories) throws SQLException,SQLStatementNullException,SQLConnectionNullException;
	
	
		//list all packagings
	Vector<Packaging> retrievePackagings() throws SQLException,SQLStatementNullException;
		//create or modify packaging
	int insertPackaging(Packaging packaging) throws SQLException,SQLStatementNullException,SQLConnectionNullException;
	int removePackagings(Packaging... packagings) throws SQLException,SQLStatementNullException,SQLConnectionNullException;
	
	
		//list all forms
	Vector<Form> retrieveForms() throws SQLException,SQLStatementNullException;
		//create or modify form
	int insertForm(Form form) throws SQLException,SQLStatementNullException,SQLConnectionNullException;
	int removeForms(Form... forms) throws SQLException,SQLStatementNullException,SQLConnectionNullException;
	
	
		//list all spicies
	Vector<Spicy> retrieveSpicies() throws SQLException,SQLStatementNullException;
		//create or modify spicy
	int insertSpicy(Spicy spicy) throws SQLException,SQLStatementNullException,SQLConnectionNullException;
	int removeSpicies(Spicy... spicies) throws SQLException,SQLStatementNullException,SQLConnectionNullException;
	
	
		//create or modify recipe
	void insertRecipe(Recipe recipe);
	void updateRecipe(Recipe recipe);
	void removeRecipes(Recipe... recipe);
		//find recipe
	Recipe findRecipe(String name);
	Recipe[] findRecipes(String... names);
	Recipe[] findRecipesByStyles(String... style);
}
