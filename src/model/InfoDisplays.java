package model;

import javafx.scene.control.Alert;

/**
 * 
 * @author Reece Grimm
 * @version 12/1/2022
 *
 */
public class InfoDisplays {
	
	/**
	 * Display Error Alert 
	 * @param mainInfo What the Error Was
	 * @param info Description of Error and/or Instructions on how to Resolve Error
	 */
	public static void displayGenericError(String mainInfo, String info) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(mainInfo);
		alert.setContentText(info);
		alert.showAndWait(); 
	}
	
	/**
	 * Display Informtation Alert 
	 * @param info Information to Give User
	 */
	public static void displayGenericInformation(String info) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(info);
		alert.showAndWait(); 
	}

}
