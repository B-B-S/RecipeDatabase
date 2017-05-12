package de.bbs.recipedatabase.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

import de.bbs.recipedatabase.exceptions.SQLConnectionNullException;
import de.bbs.recipedatabase.exceptions.SQLStatementNullException;
import de.bbs.recipedatabase.interfaces.IDAO;

/**
 * <b>This class is agnostic of its underlying database. It only requires a JDBC driver that allows transactions.</b><br>
 * To use this class, extend it by concrete classes that implement all abstract methods.<br>
 * The implementing methods of the child classes providing database access functionality
 * are expected to call the "helper methods for child class" of this class.<br>
 * <br>
 * All child classes (that are responsible for the database specific implementation) should represent singletons,
 * to avoid opening multiple connections to the same database.<br>
 * <br>
 * All public setters need to be used to pass over the connection parameters
 * before opening the connection and sql statement by calling open().<br>
 * The dataSourceType(DB type) will be passed over by the child classes's constructor as it depends on the
 * database specific implementation of the corresponding child class.<br>
 * <br>
 * When the connection and sql statement are not needed, call close() to release/close the connection.<br>
 * <br>
 * @author B-B-S
 *
 */
public abstract class AJDBCImplementsIDAO implements IDAO {

	//attributes
		//define connection parameters
	private String user;
	private String password;
	private String dataSourcePath;
		//define connection type
	private String dataSourceType;
		//define connection and statement variables
	private Connection connection;
	private Statement statement;
	
	
	//getters and setters
		//connection parameters
	public String getUser() {
		return this.user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDataSourcePath() {
		return this.dataSourcePath;
	}
	public void setDataSourcePath(String dataSourcePath) {
		this.dataSourcePath = dataSourcePath;
	}
		//only to be used internally
			//connection type
	private String getDataSourceType() {
		return this.dataSourceType;
	}
	private void setDataSourceType(String dataSourceType) {
		this.dataSourceType = dataSourceType;
	}
			//connection and statement
	private Connection getConnection() {
		return this.connection;
	}
	private void setConnection(Connection connection) {
		this.connection = connection;
	}
	private Statement getStatement() {
		return this.statement;
	}
	private void setStatement(Statement statement) {
		this.statement = statement;
	}
	
	
	//constructors
	protected AJDBCImplementsIDAO(String dataSourceType) {
		this.setDataSourceType(dataSourceType);
	}
	
	
	//standard methods
		//toString
	@Override
	public String toString() {
		return this.getClass().getSimpleName()	+ " user: " + this.getUser()
												+ " password: " + this.getPassword()
												+ " dataSourcePath: " + this.getDataSourcePath()
												+ " dataSourceType: " + this.getDataSourceType()
												+ " connection: " + this.getConnection()
												+ " statement: " + this.getStatement()
		;
	}
	
	
	//open, close methods
	public void open() throws SQLException{
			//construct connection string, hand it over to driver manager and open connection
			this.setConnection(DriverManager.getConnection(this.getDataSourceType() + this.getDataSourcePath(), this.getUser(), this.getPassword()));
			//create statement on connection
			this.setStatement(this.getConnection().createStatement());
	}
		
	public void close() throws SQLException,SQLStatementNullException,SQLConnectionNullException {
		if(this.getStatement() != null) {
			this.getStatement().close();
		} else {
			throw new SQLStatementNullException();
		}
		if(this.getConnection() != null) {
			this.getConnection().close();
		} else {
			throw new SQLConnectionNullException();
		}
	}
	
	
	//helper methods for child class
	protected int performExecuteUpdate(String sQLExpression) throws SQLException,SQLConnectionNullException,SQLStatementNullException {
		//check whether connection and statement are present
		if(this.getConnection() == null) {
			throw new SQLConnectionNullException();
		}
		if(this.getStatement() == null) {
			throw new SQLStatementNullException();
		}
		
		//initialize retry counter
		int counter = 1;
		//define save point
		Savepoint savepoint = null;
		
		//deactivate auto commit
		this.getConnection().setAutoCommit(false);
		
		//initialize return value
		int rowsAffected = 0;
		
		try {
			//set save point
			savepoint = this.getConnection().setSavepoint();
						
			//execute sql expression and assign rows affected count return value
			rowsAffected = this.getStatement().executeUpdate(sQLExpression);
			
			//commit database transaction
			this.getConnection().commit();
		} catch(SQLException e) {
			//retry operation multiple times
			//if operation still fails afterwards, abort operation
				if(counter <= 4) {
					counter++;
					this.getConnection().rollback(savepoint);
				} else {
					throw e;
				}
		} finally {
			//reactivate auto commit
			this.getConnection().setAutoCommit(true);
		}
		
		//return affected rows count
		return rowsAffected;
	}
	
	protected ResultSet performExecuteQuery(String sQLExpression) throws SQLException,SQLStatementNullException {
		//check whether statement is present
		if(this.getStatement() == null) {
			throw new SQLStatementNullException();
		}
		
		return this.getStatement().executeQuery(sQLExpression);
	}
	
	
//	//internal helper methods
//		//use in catch blocks to print sql exception debug information or general exception debug information
//	private void printException(Exception e) {
//		if(e instanceof SQLException) {
//			SQLException s = (SQLException) e;
//			System.out.println("SQLException: " + s.getMessage());
//			System.out.println("SQLState: " + s.getSQLState());
//			System.out.println("VendorError: " + s.getErrorCode());
//		} else {
//			System.out.println(e.getMessage());
//			for(StackTraceElement element : e.getStackTrace()) {
//				System.out.println(element);
//			}
//		}
//	}
}
