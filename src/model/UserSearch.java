package model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * 
 * @author Reece Grimm
 * @version 12/1/2022
 *
 */
public class UserSearch {
	
	/**
	 * Get User from Database and Set Logged in User to specified User 
	 * @param Username Username of User 
	 * @param Password Password of User 
	 * @return true if login was Successul, false otherwise 
	 */
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
				User.getInstance().logIn(rs.getInt("ID"), rs.getString("FullName"), rs.getString("Username"),
						rs.getString("Password"), rs.getString("FavsList"), rs.getString("SATScore"));
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
	
	/**
	 * Add User to Database 
	 * @param Username Username for User 
	 * @param Password Password for User 
	 * @param PasswordConf Check Password Matches 
	 * @param FirstName First Name of User 
	 * @param LastName Last Name of User 
	 * @param SATScore SAT Score of User 
	 * @return true if user was added, false if not 
	 */
	public static boolean SignUp(String Username, String Password, String PasswordConf, String FirstName, String LastName, String SATScore) {
		if(UserCreationCheckers.checkEverything(Username, Password, PasswordConf, FirstName, LastName, SATScore) && UserCreationCheckers.containsUsername(Username, 0)) {
			try {
				String Fullname = FirstName + " " + LastName; 
				int SAT = (SATScore == null || SATScore.equals(""))? 0 : Integer.parseInt(SATScore); 
				add(Username, Password, Fullname, SAT);
				return true; 
			}catch(Exception e) {
				InfoDisplays.displayGenericError("Something Went Wrong!", "Please try again later");
				return false; 
			}
		}
		return false;
	}
	
	/**
	 * Change all of Users information 
	 * @param Username Username to change to 
	 * @param Password Password to Change to 
	 * @param PasswordConf Confirm Password matches 
	 * @param Name Name to Change To 
	 * @param SATScore Score to Change To
	 * @param ID ID of user to be Updated 
	 * @return true if Update was Successful, False if not 
	 */
	public static boolean UpdateAccount(String Username, String Password, String PasswordConf, String Name, String SATScore, int ID) {
		if(UserCreationCheckers.checkEverything(Username, Password, PasswordConf, Name, Name, SATScore) && UserCreationCheckers.containsUsername(Username, ID)) {
			try {
				int SAT = (SATScore == null || SATScore.equals(""))? 0 : Integer.parseInt(SATScore); 
				update(Username, PasswordConf, Name, SAT, ID);
				return true; 
			}catch(Exception e) {
				InfoDisplays.displayGenericError("Something Went Wrong!", "Please try again later");
				return false; 
			}
		}
		return false;
	}

	/**
	 * Update Favorites List of User 
	 * @param ids String Array of favorites IDs 
	 * @param ID Users ID
	 */
	public static void setFavsList(String[] ids, int ID) {
		Connector.resetConnection();
		PreparedStatement preparedStatement = null; 
		String query = ("UPDATE users set FavsList=? where ID=?");
		try {
			preparedStatement = Connector.prepareStatement(query);
			preparedStatement.setString(1, Arrays.toString(ids));
			preparedStatement.setInt(2, ID);
			preparedStatement.executeUpdate(); 
		}catch(SQLException e) {
			InfoDisplays.displayGenericError("Error connecting to Database", "Please Try Again Later");
		}finally {
			Connector.closeConnection();
		}
		
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
	
	private static void update(String Username, String Password, String FullName, int SATScore, int ID) {
		Connector.resetConnection();
		PreparedStatement preparedStatement = null; 
		String query = ("UPDATE users set Username=?, Password=?, FullName=?, SATScore=? where ID=?");
		try {
			preparedStatement = Connector.prepareStatement(query);
			preparedStatement.setString(1, Username);
			preparedStatement.setString(2, Password);
			preparedStatement.setString(3, FullName);
			preparedStatement.setInt(4, SATScore);
			preparedStatement.setInt(5, ID);
			preparedStatement.executeUpdate(); 
		}catch(SQLException e) {
			InfoDisplays.displayGenericError("Error connecting to Datsabase", "Please Try Again Later");
		}finally {
			Connector.closeConnection();
		}
	}
	
}
