package model;

/**
 * 
 * @author Reece Grimm
 * @version 12/1/2022
 *
 */
public class Singleton {
	
	private static Singleton singleton; 
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
	
	/**
	 * Get Singleton or Create Singleton if it Does not Yet Exist
	 * @return Instance of Singleton 
	 */
	public static Singleton getInstance() {
		if(singleton == null) {
			singleton = new Singleton(); 
		}
		return singleton; 
	}
	
	/**
	 * Get Connection to School Database
	 * @return Connection 
	 */
	public Connector getConnection() {
		return connect;
	}
	
	/**
	 * Specify a School 
	 * @param ID of a Specified School 
	 */
	public void setSchoolID(String ID) {
		SchoolID = ID; 
	}
	
	/**
	 * Get ID of Currently Specified School 
	 * @return School ID
	 */
	public String getSchoolID() {
		return SchoolID;
	}
	
	/**
	 * Set if Searching by Out of State Tuition and Room and Board 
	 * @param bool true if Out of State, false if in State
	 */
	public void setOutOfState(Boolean bool) {
		OutOfState = bool; 
	}
	
	/**
	 * Set if Searching by Living off Campus 
	 * @param bool true if Off Campus, false if On Campus
	 */
	public void setOffCampus(Boolean bool) {
		OffCampus = bool; 
	}
	
	/**
	 * Check School offers Associates for Major
	 * @param bool true if offered, false if does not matter
	 */
	public void setAssociates(Boolean bool) {
		Associates = bool; 
	}
	
	/**
	 * Check School offers Bachelors for Major
	 * @param bool true if offered, false if it does not matter 
	 */
	public void setBachelors(Boolean bool) {
		Bachelors = bool; 
	}
	
	/**
	 * Check if School Requires SAT Score 
	 * @param bool true if Not Required, false if it does not matter 
	 */
	public void setRequirement(Boolean bool) {
		notRequired = bool; 
	}

	/**
	 * Get Out of State Price  
	 * @return true if Searching by out of State, false if not 
	 */
	public boolean isOutOfState() {
		return OutOfState;
	}

	/**
	 * Get Dorming off Campus Price  
	 * @return true if Searching by off campus, false if not 
	 */
	public boolean isOffCampus() {
		return OffCampus;
	}

	/**
	 * Get if searching for Associates 
	 * @return true if Searching for Associates
	 */
	public boolean isAssociates() {
		return Associates;
	}

	/**
	 * Get if searching for Bachelors 
	 * @return true if Searching for Bachelors
	 */
	public boolean isBachelors() {
		return Bachelors;
	}

	/**
	 * Get if Searching for where SAT is not required 
	 * @return true if Searching for Not Required 
	 */
	public boolean isNotRequired() {
		return notRequired;
	}

	/**
	 * Set all Special Search Params to False 
	 */
	public void resetAll() {
		OutOfState = false; 
		OffCampus = false; 
		Associates = false; 
		Bachelors = false; 
		notRequired = false; 
	}

}
