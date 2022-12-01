package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 
 * @author Reece Grimm
 * @version 12/1/2022
 *
 */
public class Connector {
	
	private static Connection conn = null; 
	
	private static Connection connector() {
		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:SchoolDatabase.db";
			Connection conn = DriverManager.getConnection(url);	
			return conn; 
		}catch(Exception e){
			e.printStackTrace();
			return null; 
		}
	}
	
	/**
	 * Connect to SQLite Database and Notify if the Connection Failed to be Created
	 */
	public Connector() {
		conn = connector();
		if(conn == null) {
			System.out.println("Connection failed");
			System.exit(1);
		}
	}
	
	/**
	 * Close Connection to SQLite Database
	 */
	public static void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Check if there is an Open Connection
	 * @return true if there is a Connection, false if Otherwise
	 */
	public boolean isDBConnected() {
		try {
			return !conn.isClosed();
		}catch(SQLException e) {
			e.printStackTrace();
			return false; 
		}
	}
	
	/**
	 * Reopen Connection if Closed
	 */
	public static void resetConnection() {
		try {
			if(conn.isClosed()) {
				conn = connector();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create Prepared Statement from Connection 
	 * @param query To Be Prepared 
	 * @return Prepared Statement of Query
	 * @throws SQLException if there is no Connection to SQLite Database
	 */
	public static PreparedStatement prepareStatement(String query) throws SQLException {
		return conn.prepareStatement(query);
	}

}
