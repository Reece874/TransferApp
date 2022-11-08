package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSearch {
	
	public static boolean login(String Username, String Password) {
		Connector.resetConnection();
		PreparedStatement preparedStatement = null; 
		String query = ("select * from users where Username=? and Password=?");
		try {
			preparedStatement = Connector.prepareStatement(query);
			preparedStatement.setString(1, Username);
			preparedStatement.setString(2, Password);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				System.out.println(rs.getString("Username"));
				return true; 
			}else {
				return false; 
			}
		}catch(SQLException e) {
			InfoDisplays.displayGenericError("Error connecting to Datsabase", "Please Try Again Later");
			return false; 
		}finally {
			Connector.closeConnection();
		}

	}
	
	public static boolean SignUp(String Username, String Password, String PasswordConf, String FirstName, String LastName, String SATScore) {
		if(UserCreationCheckers.checkEverything(Username, Password, PasswordConf, FirstName, LastName, SATScore) && confirmUniqueUser(Username)) {
			try {
				String Fullname = FirstName + " " + LastName; 
				add(Username, Password, Fullname, Integer.parseInt(SATScore));
				return true; 
			}catch(Exception e) {
				InfoDisplays.displayGenericError("Something Went Wrong!", "Please try again later");
				return false; 
			}
		}
		return false;
	}
	
	private static void add(String Username, String Password, String FullName, int SATScore) {
		Connector.resetConnection();
		PreparedStatement preparedStatement = null; 
		String query = ("insert into users (Username, Password, FullName, SATScore, FavsList) VALUES(?, ?, ?, ?, '0 0 0 0 0 0 0 0 0 0')");
		try {
			preparedStatement = Connector.prepareStatement(query);
			preparedStatement.setString(1, Username);
			preparedStatement.setString(2, Password);
			preparedStatement.setString(3, FullName);
			preparedStatement.setInt(4, SATScore);
			preparedStatement.executeUpdate(); 
		}catch(SQLException e) {
			InfoDisplays.displayGenericError("Error connecting to Datsabase", "Please Try Again Later");
		}finally {
			Connector.closeConnection();
		}
	}
	
	private static boolean confirmUniqueUser(String Username) {
		Connector.resetConnection();
		PreparedStatement preparedStatement = null; 
		String query = ("select * from users where Username=?");
		try {
			preparedStatement = Connector.prepareStatement(query);
			preparedStatement.setString(1, Username);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				System.out.println(rs.getString("Username"));
				return false; 
			}else {
				return true; 
			}
		}catch(SQLException e) {
			InfoDisplays.displayGenericError("Error connecting to Datsabase", "Please Try Again Later");
			return false; 
		}finally {
			Connector.closeConnection();
		}
	}
	
}
