package model;

public class TableItem {
	
	private String ID, SchoolName, City, State; 
	
	public TableItem(String ID, String SchoolName, String City, String State) {
		this.ID = ID; 
		this.SchoolName = SchoolName; 
		this.City = City; 
		this.State = State; 
	}

	public String getID() {
		return ID;
	}

	public String getSchoolName() {
		return SchoolName;
	}

	public String getCity() {
		return City;
	}

	public String getState() {
		return State;
	}

}
