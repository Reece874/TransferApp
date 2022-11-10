package model;

import java.util.Arrays;

public class User {
	
	private int ID; 
	private String FullName;
	private String Username; 
	private String Password; 
	private String[] FavsList; 
	
	public User(int ID, String FullName, String Username, String Password, String favsList) {
		this.ID = ID; 
		this.FullName = FullName; 
		this.Username = Username; 
		this.Password = Password; 
		this.FavsList = favsList.replaceAll("[^0-9 ]", "").split(" "); 
	}
	
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

	@Override
	public String toString() {
		return "User [ID=" + ID + ", FullName=" + FullName + ", Username=" + Username + ", Password=" + Password
				+ ", FavsList=" + Arrays.toString(FavsList) + "]";
	}
	
	

}
