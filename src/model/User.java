package model;

import java.util.Arrays;

public class User {
	
	private int ID; 
	private String FullName;
	private String Username; 
	private String Password; 
	private String[] FavsList; 
	private String SAT; 
	
	public User(int ID, String FullName, String Username, String Password, String favsList, String SAT) {
		this.ID = ID; 
		this.FullName = FullName; 
		this.Username = Username; 
		this.Password = Password; 
		this.FavsList = favsList.replaceAll("[^0-9 ]", "").split(" "); 
		this.SAT = SAT;
	}
	
	public String[] getFavs() {
		return FavsList; 
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getFullName() {
		return FullName;
	}

	public void setFullName(String fullName) {
		FullName = fullName;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String[] getFavsList() {
		return FavsList;
	}

	public void setFavsList(String[] favsList) {
		FavsList = favsList;
	}

	public void setSAT(String sAT) {
		SAT = sAT;
	}

	public String getSAT() {
		return SAT;
	}
	
	public boolean isFav(String id) {
		for (int i = 0; i < FavsList.length; i++) {
			if(FavsList[i].equals(id)) {
				System.out.println(Arrays.toString(FavsList));
				return true; 
			}
		}
		return false; 
	}
	
	public boolean toggleFavorite(String id) {
		System.out.println("Toggle Ran");
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

	@Override
	public String toString() {
		return "User [ID=" + ID + ", FullName=" + FullName + ", Username=" + Username + ", Password=" + Password
				+ ", FavsList=" + Arrays.toString(FavsList) + "]";
	}
	
	

}
