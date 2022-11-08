package model;

import javafx.scene.control.Alert;

public class InfoDisplays {
	
	public static void displayGenericError(String mainInfo, String info) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(mainInfo);
		alert.setContentText(info);
		alert.showAndWait(); 
	}
	
	public static void displayGenericInformation(String info) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(info);
		alert.showAndWait(); 
	}

}
