package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author Reece Grimm
 * @version 12/1/2022
 *
 */
public class UserCreationCheckers {
	
	/**
	 * Check all Information is Valid
	 * @param Username Potential Username 
	 * @param Password Potential Password 
	 * @param PasswordConf Password to check Matching 
	 * @param FirstName Potential First Name 
	 * @param LastName Potential Last Name 
	 * @param SAT SAT Score of User 
	 * @return true if User Can be Created, False if Not 
	 */
	public static boolean checkEverything(String Username, String Password, String PasswordConf, String FirstName, String LastName, String SAT) {
		StringBuilder info = new StringBuilder(); 
		
		if(!confirmPassword(Password, PasswordConf)) {
			info.append("Passwords do Not Match\n");
		}
		
		if(!checkParams(Username, Password, FirstName, LastName)) {
			info.append("-All Fields Except SAT Must be Filled Out\n"); 
		}
		if(!checkUsername(Username)) {
			info.append("-Username Must be at Least 6 Characters Long\n"); 
		}
		if(!checkPassword(Password)) {
			info.append("-Password must be at least 6 Characters Long\n"
					+ "-Password Must contain at least 1 lower case letter\n"
					+ "-Password Must contain at least 1 upper case letter\n"
					+ "-Password Must contain at least 1 number\n");
		}
		
		if(!isSatValid(SAT)) {
			info.append("-SAT must be an integer between 0 and 1600 or Left Blank");
		}
		
		String fin = info.toString();
		if(fin.length() > 0) {
			InfoDisplays.displayGenericError("Something is not Filled Out Correctly!", fin); 
			return false; 
		}
			return true; 		
	}
	
	/**
	 * Check if Username is Already Taken 
	 * @param user Potential Username for user 
	 * @param ID ID of student wishing to change Username 
	 * @return true if Username can be changed, false if not 
	 */
    public static boolean containsUsername(String user, int ID) {
    	Connector.resetConnection();
        PreparedStatement preparedStatement = null; 
        ResultSet results = null; 
        String query = "select * from users where Username=?";
        try {
            preparedStatement = Connector.prepareStatement(query);
            preparedStatement.setString(1, user);
            results = preparedStatement.executeQuery();
            if(!results.next() || results.getInt("ID") == ID) {
                return true; 
            }
            
          InfoDisplays.displayGenericInformation("Username is already in use");
          return false; 
        }catch(SQLException e) {
            InfoDisplays.displayGenericError("Something Went Wrong", "Please Try Again later");
            return false; 
        }finally {
        	Connector.closeConnection();
        }
        
    }
	
	private static boolean checkParams(String Username, String Password, String FirstName, String LastName) {
		if(Username == "" || Password == "" || FirstName == "" || LastName == "") {
			return false; 
		}
		return true;
	}
	
	private static boolean confirmPassword(String password, String PasswordConf) {
		if(password.equals(PasswordConf)) {
			return true; 
		}
		return false; 
	}

	private static boolean checkUsername(String Username) {
		if(Username.length() < 6) {
			return false; 
		}	
		return true; 
	}
	
	private static boolean checkPassword(String Password) {
		if(Password.length() < 6) {
			return false; 
		}	
		char[] chars = Password.toCharArray(); 
		return findPasswordRequierments(chars);
	}
	
	private static boolean findPasswordRequierments(char[] chars) {
		boolean lowerCase = false; 
		boolean upperCase = false; 
		boolean digit = false; 
		
		for (char c : chars) {
				if(!lowerCase && Character.isLowerCase(c)) {
					lowerCase = true; 
				}

				if(!upperCase && Character.isUpperCase(c)) {
					upperCase = true; 
				}
				
				if(!digit && Character.isDigit(c)) {
					digit = true; 
				}
				if(lowerCase && upperCase && digit) {
					return true; 
				}
		}
		
		return (lowerCase && upperCase && digit)? true:false; 
	}
	
	private static boolean isSatValid(String SAT) {
		if(SAT == "") {
			return true; 
		}
		try {
			int x = Integer.parseInt(SAT);
			if( x > 1600 || x < 0) {
				return false; 
			}
		}catch(Exception e) {
			return false; 
		}
		return true;
	}

}
