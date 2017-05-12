package de.bbs.recipedatabase.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import de.bbs.recipedatabase.dao.Implementation.JDBCMySQL;
import de.bbs.recipedatabase.dao.Implementation.Style;
import de.bbs.recipedatabase.exceptions.SQLConnectionNullException;
import de.bbs.recipedatabase.exceptions.SQLStatementNullException;
import de.bbs.recipedatabase.interfaces.IDAO;

public class Main {

	public static void main(String[] args) {
//		Scanner scan = null;
//		Statement statement = null;
//		Connection connection = null;
//		
//		try {
//			//pass the driver to the driver manager
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch(Exception e) {
//			System.out.println(e);
//			e.printStackTrace();
//		}
//		
//		try {
//			//open scanner
//			scan = new Scanner(new File("TestData1.sql"));
//			
//			//get Connection to database
//			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/recipedatabase","teilnehmer","teilnehmer");
//			
//			//create statement
//			statement = connection.createStatement();
//			
//			//write records to database
//			while(scan.hasNextLine()) {
//				String sql = scan.nextLine();
//				statement.executeUpdate(sql);
//			}
//		} catch(FileNotFoundException e) {
//			System.out.println(e);
//			e.printStackTrace();
//		} catch(SQLException e) {
//			//handle sql errors
//			System.out.println("SQLException: " + e.getMessage());
//		    System.out.println("SQLState: " + e.getSQLState());
//		    System.out.println("VendorError: " + e.getErrorCode());
//		} finally {
//			//close scanner and connection
//			try {
//				scan.close();
//				statement.close();
//				connection.close();
//			} catch(Exception e) {
//				//do nothing
//			}
//		}
		
		
		
		
//		IDAO daoInstance = JDBCMySQL.getDAOInstance();
//		
//		daoInstance.setDataSourcePath("localhost:3306/recipedatabase");
//		daoInstance.setUser("teilnehmer");
//		daoInstance.setPassword("teilnehmer");
//		
//		
//		Style style = new Style("belgian");
//		Style style1 = new Style("belgian1");
//		Style style2 = new Style("belgian2");
//		
//		try {
//			daoInstance.open();
//			
//			//test Style
//			daoInstance.insertStyle(style);
//			daoInstance.insertStyle(style1);
//			daoInstance.insertStyle(style2);
//			System.out.println(daoInstance.retrieveStyles());
//			daoInstance.removeStyles(style1,style);
//			System.out.println(daoInstance.retrieveStyles());
//		} catch(SQLException e) {
//			System.out.println("Error in database connection!");
//			System.out.println("SQLException: " + e.getMessage());
//		    System.out.println("SQLState: " + e.getSQLState());
//		    System.out.println("VendorError: " + e.getErrorCode());
//		    e.printStackTrace();
//		} catch(SQLStatementNullException | SQLConnectionNullException e) {
//			System.out.println("Error in database operation/connection!");
//			System.out.println(e.getMessage());
//		} finally {
//			try {
//				daoInstance.close();
//			} catch(SQLException e) {
//				System.out.println("Error in database connection, could not close connection!");
//			} catch(SQLStatementNullException | SQLConnectionNullException e) {
//				//do nothing (connection and/or SQL statement is null, because error occurred beforehand)
//			}
//		}
	}
}
