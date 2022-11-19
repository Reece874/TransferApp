package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Connector {
	
	private static Connection conn = null; 
	
	public static Connection connector() {
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
	
	public Connector() {
		conn = connector();
		if(conn == null) {
			System.out.println("Connection failed");
			System.exit(1);
		}
	}
	
	public static void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isDBConnected() {
		try {
			return !conn.isClosed();
		}catch(SQLException e) {
			e.printStackTrace();
			return false; 
		}
	}
	
	public static void resetConnection() {
		try {
			if(conn.isClosed()) {
				conn = connector();
				if(conn == null) {
					System.out.println("Connection Failed");
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static PreparedStatement prepareStatement(String query) throws SQLException {
		return conn.prepareStatement(query);
	}

}
