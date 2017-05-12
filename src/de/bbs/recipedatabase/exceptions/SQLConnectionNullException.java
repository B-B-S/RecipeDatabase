package de.bbs.recipedatabase.exceptions;

public class SQLConnectionNullException extends Exception {

	//constructors
	public SQLConnectionNullException() {
		super("SQL connection object is null when expected to be present!");
	}
}
