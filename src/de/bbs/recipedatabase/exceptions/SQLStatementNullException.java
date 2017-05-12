package de.bbs.recipedatabase.exceptions;

public class SQLStatementNullException extends Exception {

	//constructors
	public SQLStatementNullException () {
		super("SQL statement object is null when expected to be present!");
	}
}
