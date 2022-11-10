package model;

public class Singleton {
	
	private static Singleton singleton; 
	private User user; 
	private String SchoolID; 
	private boolean OutOfState;
	private boolean OffCampus; 
	private boolean Associates; 
	private boolean Bachelors; 
	private boolean notRequired; 
	private Connector connect; 
	
	private Singleton() {
		connect = new Connector(); 
	}
	
	public static Singleton getInstance() {
		if(singleton == null) {
			singleton = new Singleton(); 
		}
		return singleton; 
	}
	
	public void setUser(int ID, String FullName, String Username, String Password, String favsList, String SAT) {
		user = new User(ID, FullName, Username, Password, favsList, SAT);
	}
	
	public void setFavs() {
		UserSearch.setFavsList(user.getFavs(),user.getId());
	}
	
	public String[] getUserFavs() {
		return user.getFavs();
	}
	
	public String getSAT() {
		return user.getSAT();
	}
	
	public String getFullName() {
		return user.getFullName();
	}
	
	public boolean isFavoriteSchool() {
		return user.isFav(SchoolID);
	}
	
	public boolean toggle(String id) {
		return user.toggleFavorite(id);
	}
	
	public boolean isUserSignedIn() {
		if(user == null) {
			return false; 
		}
		return true;
	}
	
	public Connector getConnection() {
		return connect;
	}
	
	public void setSchoolID(String ID) {
		SchoolID = ID; 
	}
	
	public String getSchoolID() {
		return SchoolID;
	}
	
	public void setOutOfState(Boolean bool) {
		OutOfState = bool; 
	}
	
	public void setOffCampus(Boolean bool) {
		OffCampus = bool; 
	}
	
	public void setAssociates(Boolean bool) {
		Associates = bool; 
	}
	
	public void setBachelors(Boolean bool) {
		Bachelors = bool; 
	}
	
	public void setRequirement(Boolean bool) {
		notRequired = bool; 
	}

	public boolean isOutOfState() {
		return OutOfState;
	}

	public boolean isOffCampus() {
		return OffCampus;
	}

	public boolean isAssociates() {
		return Associates;
	}

	public boolean isBachelors() {
		return Bachelors;
	}

	public boolean isNotRequired() {
		return notRequired;
	}

	public void resetAll() {
		OutOfState = false; 
		OffCampus = false; 
		Associates = false; 
		Bachelors = false; 
		notRequired = false; 
	}

}
