package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SchoolSearch {
	
	public static void test() {
		Connector.resetConnection();
		Statement statement;
		String query = "select * from users"; 
		try {
			PreparedStatement ps = Connector.prepareStatement(query);
			ResultSet result = ps.executeQuery();
			String[] favs = result.getString("FavsList").split(" "); 
			System.out.println(Arrays.toString(favs));
			System.out.println(result.getString("FavsList"));
		}catch(SQLException e){
			System.out.println("Something went Wrong");
		}finally {
			Connector.closeConnection();
		}
	}

	
	public static String[] getItems(String ID) {
		Connector.resetConnection();
		String query = "select * from schools "
				+ "JOIN pricing ON schools.TablesKey = pricing.ID "
				+ "JOIN academics ON schools.TablesKey = academics.ID "
				+ "JOIN people ON schools.TablesKey = people.ID "
				+ "WHERE SchoolID=?";
		try {
			PreparedStatement ps = Connector.prepareStatement(query);
			ps.setString(1, ID);
			ResultSet result = ps.executeQuery();
			String[] items = {result.getString("SchoolName"), replacers.replace(result.getString("City")), replacers.replace(result.getString("State")),
					replacers.replace(result.getString("SchoolZip")), replacers.replace(result.getString("HighestDegree")),
					replacers.replace(result.getString("PredominantDegree")),replacers.replace(result.getString("InStateTuition")),
					replacers.replace(result.getString("OutOfStateTuition")), replacers.replace(result.getString("RoomBoardOnCampus")), 
					replacers.replace(result.getString("RoomBoardOffCampus")),replacers.replace(result.getString("StudentPopulation")),
					replacers.replace(result.getString("SATScore")),replacers.replace(result.getString("PellGrantRate")), 
					replacers.replace(result.getString("Religion")), replacers.readOwn(result.getString("PrivateOrPublic")),
					replacers.replace(result.getString("AdmissionsRateOverall")), replacers.replace(result.getString("CompletionCohort")),
					result.getString("Region"), result.getString("TestRequire"), result.getString("Locale"),
					result.getString("MajorsOffered")};
			System.out.println(result.getString("StudentPopulation"));
			Connector.closeConnection();
			return items; 		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public static ObservableList<TableItem> getAll(){
		return searchParams(null, null, null, null, null, null, 0, 0, null, null, null, null, null, null, 0, 0, null, null); 
	}
	
	public static ObservableList<TableItem> searchParams(String ID, String Name, String City, String State, 
			String Highest, String Predominant, int Tuition, int RoomBoard, String minPopulation, String maxPopulation, 
			String Major, String SATScore, String religion, String Ownership, int PellGrant, int Admission, String Region, String Locale){
		
		Connector.resetConnection();
		ObservableList<TableItem> list = FXCollections.observableArrayList();
		StringBuilder query = new StringBuilder("select SchoolID, SchoolName, City, State from schools "
				+ "JOIN pricing ON schools.TablesKey = pricing.ID "
				+ "JOIN academics ON schools.TablesKey = academics.ID "
				+ "JOIN people ON schools.TablesKey = people.ID "
				+ " WHERE");
		query.append(searchID(ID) + searchName(Name) + searchCity(City) + searchState(State) 
		+ searchHighest(Highest) + searchPredominant(Predominant) + searchTuition(Tuition) + searchRoomBoard(RoomBoard) 
		+ searchPopulation(minPopulation, maxPopulation) + searchMajor(Major) + searchSAT(SATScore) + searchReligion(religion)
		+ searchOwnership(Ownership) + searchAdmission(Admission) + searchPellGrant(PellGrant) + searchNotRequired()
		+ searchRegion(Region) + searchLocale(Locale));
		try {
			list = getList(query, list);
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Something went Wrong");
		}
		Connector.closeConnection();
		return list; 
	}
	
	private static String searchID(String ID) {
		if(ID == null || ID.trim().isEmpty()) {
			return ""; 
		}
		try {
			int id = Integer.parseInt(ID); 
			return " SchoolID=" + id + " and"; 
		}
		catch(Exception e){
			InfoDisplays.displayGenericError("ID must be an Integer!", "Search will continue without an ID");
			return "";
		}
		
	}
	
	private static String searchName(String Name) {
		if(Name == null || Name.trim().isEmpty()) {
			return ""; 
		}
		return " SchoolName LIKE '%" + Name + "%' and"; 
	}
	
	private static String searchCity(String City) {
		if(City == null || City.trim().isEmpty()) {
			return ""; 
		}
		return " City LIKE '%" + City + "%' and"; 
	}
	
	private static String searchState(String State) {
		if(State == null || State.equals("--")) {
			return ""; 
		}
		return " State='" + State + "' and"; 
	}
	
	private static String searchRegion(String Region) {
		if(Region == null || Region.equals("--")) {
			return ""; 
		}
		return " Region='" + Region + "' and"; 
	}
	
	private static String searchLocale(String Locale) {
		if(Locale == null || Locale.equals("--")) {
			return ""; 
		}
		return " Locale='" + Locale + "' and"; 
	}
	
	private static String searchHighest(String Highest) {
		if(Highest == null || Highest.equals("--")) {
			return "";
		}
		return " HighestDegree LIKE '%" + Highest + "%' and"; 
	}
	
	private static String searchPredominant(String Predominant) {
		if(Predominant == null || Predominant.equals("--")) {
			return "";
		}
		return " PredominantDegree LIKE '%" + Predominant + "%' and"; 
	}
	
	private static String searchTuition(int Tuition) {
		if(Tuition == 0) {
			return ""; 
		}
		if(Singleton.getInstance().isOutOfState()) {
			return " OutOfStateTuition <=" + Tuition + " and"; 
		}
		return " InStateTuition <=" + Tuition + " and"; 
	}
	
	private static String searchPellGrant(int pellGrant) {
		if(pellGrant == 0) {
			return ""; 
		}	
		return " PellGrantRate >=" + pellGrant + " and";
	}
	
	private static String searchAdmission(int admission) {
		if(admission == 0) {
			return ""; 
		}
		
		return " AdmissionsRateOverall >=" + admission + " and";
	}
	
	private static String searchRoomBoard(int RoomBoard) {
		if(RoomBoard == 0) {
			return ""; 
		}
		if(Singleton.getInstance().isOffCampus()) {
			return " RoomBoardOffCampus <=" + RoomBoard + " and"; 
		}
		return " RoomBoardOnCampus <=" + RoomBoard + " and"; 
	}
	
	private static String searchPopulation(String min, String max) {
		if(min == null || min == "") {
			min = "0"; 
		}
		if (max == null || max == "") {
			max = "0";
		}
		try {
			int minimum = Integer.parseInt(min);
			int maximum = Integer.parseInt(max);
			if(maximum<minimum || maximum==0 && minimum != 0) {
				return " StudentPopulation >= " + min + " and StudentPopulation !='null' and";
			}
			if(maximum != 0) {
				return " StudentPopulation >= " + min + " and" + " StudentPopulation <= " + max + " and"; 
			}
			return "";
		}catch(Exception e) {
			InfoDisplays.displayGenericError("Student population must be integers!", "Search will continue without population.");
			System.out.println(min + " " + max);
			return ""; 
		}
	}
	
	private static String searchSAT(String SAT) {
		if(SAT == null || SAT.equals("")) {
			return "";
		}
		try {
			int sat = Integer.parseInt(SAT); 
			if(sat == 0) {
				return ""; 
			}
			return " SATScore <= " + SAT + " and"; 
		}catch(Exception e) {
			InfoDisplays.displayGenericError("SAT score must be integers!", "Search will continue without SAT.");
			return ""; 
		}
	}
	
	private static String searchMajor(String major) {
		if(major == null || major.equals("--")) {
			return ""; 
		}
		if(Singleton.getInstance().isBachelors() && Singleton.getInstance().isAssociates()) {
			return " MajorsOffered Like '%Bachelors in " + major + "%' and MajorsOffered Like '%Associates in " + major + "%' and";  
		}
		if(Singleton.getInstance().isBachelors()) {
			return " MajorsOffered Like '%Bachelors in " + major + "%' and"; 
		}
		if(Singleton.getInstance().isAssociates()) {
			return " MajorsOffered Like '%Associates in " + major + "%' and"; 
		}
		return " MajorsOffered Like '%" + major + "%' and";  
	}
	
	private static String searchReligion(String religion) {
		if(religion == null || religion.equals("--")) {
			return ""; 
		}
		return " Religion='" + religion + "' and";
	}
	
	private static String searchOwnership(String ownership) {
		if(ownership == null) {
			return ""; 
		}
		switch(ownership) {
		case "Public":
			return " PrivateOrPublic=1 and"; 
		case "Private":
			return " PrivateOrPublic=2 and";
		case "Proprietary":
			return " PrivateOrPublic=3 and"; 
		default:
			return ""; 
		}	
	}
	
	private static String searchNotRequired() {
		if(Singleton.getInstance().isNotRequired()) {
			return " TestRequire != 'Required' and TestRequire != 'Unknown' and";
		}
		return ""; 
	}
	
	private static String createFinalQuery(StringBuilder query) {
		String finalQuery = query.toString().substring(0, query.lastIndexOf(" ")); 
		System.out.println(finalQuery);
		return finalQuery; 
	}
	
	private static ObservableList<TableItem> getList(StringBuilder query, ObservableList<TableItem> list) throws SQLException{
		
		PreparedStatement preparedStatement = null;
		preparedStatement = Connector.prepareStatement(createFinalQuery(query));
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()) {
			list.add(new TableItem(rs.getString("SchoolID"), rs.getString("SchoolName"), rs.getString("City"), rs.getString("State")));
		}
		return list; 
	}

}
