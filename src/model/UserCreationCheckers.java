package model;

public class UserCreationCheckers {
	
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
	
	public static boolean checkUsername(String Username) {
		if(Username.length() < 6) {
			return false; 
		}	
		return true; 
	}
	
	public static boolean checkPassword(String Password) {
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
