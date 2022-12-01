package model;

/**
 * 
 * @author Reece Grimm
 * @version 12/1/2022
 *
 */
public class TableItem {
	
	private String ID, SchoolName, City, State; 
	
	/**
	 * Brief Info of School to be Displayed on Table
	 * @param ID ID of School
	 * @param SchoolName School Name
	 * @param City City the School is In
	 * @param State State the School is In
	 */
	public TableItem(String ID, String SchoolName, String City, String State) {
		this.ID = ID; 
		this.SchoolName = SchoolName; 
		this.City = City; 
		this.State = State; 
	}

	/**
	 * Get the ID of a School
	 * @return ID as a String
	 */
	public String getID() {
		return ID;
	}

	/**
	 * Get the Name of a School
	 * @return School Name
	 */
	public String getSchoolName() {
		return SchoolName;
	}

	/**
	 * Get the City the School is in
	 * @return City Name
	 */
	public String getCity() {
		return City;
	}

	/**
	 * Get the State the School is in
	 * @return State Abbreviation 
	 */
	public String getState() {
		return State;
	}

}
