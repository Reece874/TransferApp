package model;

/**
 * 
 * @author Reece Grimm
 * @version 12/1/2022
 *
 */
public class replacers {
	
	/**
	 * Replace "Null", "0", and "0.0" with "N/A"
	 * @param str String to Be Checked if is an "Empty Value" 
	 * @return "N/A" if String was an "Empty Value" or the String that was Entered
	 */
	public static String replace(String str) {
		if(str.equals("null")|| str.equals("0") || str.equals("0.0")) {
			return "N/A"; 
		}
		return str; 
	}
	
	/**
	 * Replace Integer in String to Corresponding Ownership Value
	 * @param str String to be Changed.  String Should only Contain an Integer, will return N/A otherwise 
	 * @return "1" = "Public", "2" = "Private", "3" = "Proprietary", Otherwise Return "N/A"
	 */
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
