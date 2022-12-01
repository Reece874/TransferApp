package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * @author Reece Grimm
 * @version 12/1/2022
 *
 */

public class ComboLists {
	private static final ObservableList<String> STATES = 
			FXCollections.observableArrayList(
					"--", "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DC", "DE", "FL", "GA", "HI", "ID", "IL", "IN",
					"IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NC", "ND", "NE", 
					"NH", "NJ","NM", "NV", "NY", "OH", "OK", "OR", "PA", "PR", "RI", "SC", "SD", "TN", "TX", "UT", 
					"VT", "VA", "WA", "WI", "WV", "WY" 
					);
	
	private static final ObservableList<String> DEGREE = 
			FXCollections.observableArrayList("--", "Certificate", "Associates", "Bachelors", "Graduate");
	
	private static final ObservableList<String> OWNERSHIP = 
			FXCollections.observableArrayList("--", "Public", "Private", "Proprietary");
	
	private static final ObservableList<String> MAJOR = 
			FXCollections.observableArrayList("--", "Agriculture", "Architecture", "Communication", "Communications Technology",
					"Computer Science", "Education", "Engineering", "Foreign Language", "Legal Studies", "English", "Humanities",
					"Biology", "Mathematics", "Physical Science", "Science Technology", "Business Marketing", "History");

	private static final ObservableList<String> RELIGION = 
			FXCollections.observableArrayList("--", "None", "Roman Catholic", "Christian", "Methodist", "Friends", "Protestant", "Baptist", "Jewish",
					"Orthodox", "Church of God", "Church of Brethren", "Church of Nazarene", "Cumberland Presbyterian", "Presbyterian Church",
					"Lutheran Church", "Mennonite Church", "Chrurches Of Christ", "United Brethren Church", "Missionary Church",
					"Undenominational", "Wesleyan", "Orthodox");
	
	private static final ObservableList<String> REGION = 
			FXCollections.observableArrayList("--", "New England", "Mid East", "Great Lakes", "Plains", "Southeast",
					"Southwest", "Rocky Mountains", "Far West", "Outlying Areas");
	
	private static final ObservableList<String> LOCALE = 
			FXCollections.observableArrayList("--", "City", "Suburb", "Town", "Rural");
	
	/**
	 * Get Observable List of all US State Abbreviations
	 * @return State Abbreviations List
	 */
	public static ObservableList<String> getStates(){
		return STATES; 
	}
	
	/**
	 * Get Observable List of all Degrees that Could be Offered at a School
	 * @return Degrees List
	 */
	public static ObservableList<String> getDegrees(){
		return DEGREE; 
	}
	
	/**
	 * Get Observable List of Most Majors that Could be Offered at a School
	 * @return Majors List
	 */
	public static ObservableList<String> getMajors(){
		return MAJOR; 
	}
	
	/**
	 * Get Observable List of Most Religions a School May be Associated Under
	 * @return Religion List
	 */
	public static ObservableList<String> getReligions(){
		return RELIGION;
	}
	
	/**
	 * Get Observable List of all Degrees of Ownership a School May Have 
	 * @return Ownership List
	 */
	public static ObservableList<String> getOwnership(){
		return OWNERSHIP;
	}
		
	/**
	 * Get Observable List of all Locale's a School Could be In
	 * @return Locale List
	 */
	public static ObservableList<String> getLocale(){
		return LOCALE;
	}
	
	/**
	 * Get Observable List of all US Regions
	 * @return Region List
	 */
	public static ObservableList<String> getRegion(){
		return REGION;
	}

}
