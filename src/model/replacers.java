package model;

public class replacers {
	
	public static String replace(String str) {
		if(str.equals("null")|| str.equals("0") || str.equals("0.0")) {
			return "N/A"; 
		}
		return str; 
	}
	
	public static String readOwn(String str) {
		if(str.equals("1")) {
			return "Public"; 
		}
		if(str.equals("2")) {
			return "Private"; 
		}
		if(str.equals("3")) {
			return "Proprietary"; 
		}
		return "N/A";
	}

}
