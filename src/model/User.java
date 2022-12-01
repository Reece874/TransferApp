package model;

import java.util.Arrays;

/**
 * 
 * @author Reece Grimm
 * @version 12/1/2022
 *
 */
public class User {
	
	private static User user; 
	private int ID; 
	private String FullName;
	private String Username; 
	private String Password; 
	private String[] FavsList; 
	private String SAT; 
	
	private User() {
		
	}
	
	/**
	 * Get Instance of Logged In User.  Only One User can be Logged in 
	 * @return Logged in User 
	 */
	public static User getInstance() {
		if(user == null) {
			user = new User();
		}
		return user; 
	}
	
	/**
	 * Set Information of Logged in Student 
	 * @param ID User ID
	 * @param FullName User's Name 
	 * @param Username User's Username 
	 * @param Password User's Password 
	 * @param favsList User's list of Favorite Schools 
	 * @param SAT User's SAT Score
	 */
	public void logIn(int ID, String FullName, String Username, String Password, String favsList, String SAT) {
		this.ID = ID; 
		this.FullName = FullName; 
		this.Username = Username; 
		this.Password = Password; 
		this.FavsList = favsList.replaceAll("[^0-9 ]", "").split(" "); 
		this.SAT = SAT;
	}
	
	/**
	 * Check if User is Signed In 
	 * @return true if User is Signed in. False if not 
	 */
	public static boolean isUserSignedIn() {
		if(user == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * Get Users Favorited Schools 
	 * @return String Array of Favorite Schools ID's 
	 */
	public String[] getFavs() {
		return FavsList; 
	}
	
	/**
	 * Retrieve Updated Favorite List from Database 
	 */
	public void setFavs() {
	UserSearch.setFavsList(this.FavsList, this.ID);
  }
	
	/**
	 * Get ID of User 
	 * @return User ID
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Set ID of User 
	 * @param iD User ID
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * Get Full Name of User 
	 * @return User's Name 
	 */
	public String getFullName() {
		return FullName;
	}

	/**
	 * Set Name of User 
	 * @param fullName To Change Users Name to 
	 */
	public void setFullName(String fullName) {
		FullName = fullName;
	}

	/**
	 * Get User's Username 
	 * @return Username 
	 */
	public String getUsername() {
		return Username;
	}
	/**
	 * Set User's Username 
	 * @param username To Change Username to 
	 */
	public void setUsername(String username) {
		Username = username;
	}

	/**
	 * Get User's Password 
	 * @return Password  
	 */
	public String getPassword() {
		return Password;
	}

	/**
	 * Set User's Password 
	 * @param password Password To Change Password to 
	 */
	public void setPassword(String password) {
		Password = password;
	}

	/**
	 * Set User's Favorites List  
	 * @param favsList New Favorites List for User 
	 */
	public void setFavsList(String[] favsList) {
		FavsList = favsList;
	}

	/**
	 * Set User's SAT Score 
	 * @param sAT Score To Change to 
	 */
	public void setSAT(String sAT) {
		SAT = sAT;
	}

	/**
	 * Get User's SAT Score 
	 * @return Users SAT Score as String   
	 */
	public String getSAT() {
		return SAT;
	}
	
	/**
	 * Change all of Users information 
	 * @param Username Username to change to 
	 * @param Password Password to Change to 
	 * @param PasswordConf Confirm Password matches 
	 * @param Name Name to Change To 
	 * @param SAT Score to Change To
	 * @return true if Update was Successful, False if not 
	 */
	public boolean updateAccount(String Username, String Password, String PasswordConf, String Name, String SAT) {
		if(UserSearch.UpdateAccount(Username, Password, PasswordConf, Name, SAT, this.ID)) {
			this.Username = Username; 
			this.Password = Password;
			this.FullName = Name;
			this.SAT = SAT; 
			return true; 
		}
		return false; 
	}
	
	/**
	 * Check if School is In Users Favorites List 
	 * @param id ID of School 
	 * @return true if School is favorited, false if not 
	 */
	public boolean isFav(String id) {
		for (int i = 0; i < FavsList.length; i++) {
			if(FavsList[i].equals(id)) {
				System.out.println(Arrays.toString(FavsList));
				return true; 
			}
		}
		return false; 
	}
	
	/**
	 * Add School to Favorites List if Not Currently on.  Remove otherwise 
	 * @param id ID of School To Toggle 
	 * @return True if Operation was Successful 
	 */
	public boolean toggleFavorite(String id) {
		int pos = -1; 
		for (int i = 0; i < FavsList.length; i++) {
			if(FavsList[i].equals("0")) {
				pos = i; 
			}
			if(FavsList[i].equals(id)) {
				FavsList[i] = "0"; 
				System.out.println(Arrays.toString(FavsList));
				return true; 
			}
		}
		if(pos > -1) {
			FavsList[pos] = id; 
			System.out.println(Arrays.toString(FavsList));
			return true; 
		}else {
			InfoDisplays.displayGenericInformation("You can only have 10 Favorited Schools!");
			return false; 
		}
	}

}
