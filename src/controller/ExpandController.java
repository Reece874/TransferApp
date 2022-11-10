package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.SchoolSearch;
import model.Singleton;

public class ExpandController implements Initializable{
	
	@FXML
	private Label LblSchoolName; 
	
	@FXML
	private Label LblSchoolID; 
	
	@FXML
	private Label LblCity; 
	
	@FXML
	private Label LblState;
	
	@FXML
	private Label LblZip; 
	
	@FXML
	private Label LblHighest;
	
	@FXML
	private Label LblDominant; 
	
	@FXML
	private Label LblInState; 
	
	@FXML
	private Label LblOutState; 
	
	@FXML
	private Label LblOnCamp; 
	
	@FXML
	private Label LblOffCamp; 
	
	@FXML
	private Label LblPopulation; 
	
	@FXML
	private Label LblSAT; 
	
	@FXML
	private Label LblPellGrant; 
	
	@FXML
	private Label LblReligion;
	
	@FXML
	private Label LblOwnership; 
	
	@FXML
	private Label LblAdmission; 
	
	@FXML
	private Label LblCompletion; 
	
	@FXML
	private Label LblRegion; 
	
	@FXML
	private Label LblTestRequire; 
	
	@FXML
	private Label LblLocale; 
	
	@FXML
	private Label LblFavorite;
	
	@FXML
	private TextArea TAMajors; 

	
	public void Cancel(ActionEvent actionevent) {
		SwapScene(actionevent, "/view/SchoolView.fxml", "College Search");
	}
	
	public void Toggle() {
		if(Singleton.getInstance().toggle(LblSchoolID.getText().substring(LblSchoolID.getText().lastIndexOf(" ")+1))) {
			LblFavorite.setVisible(!LblFavorite.isVisible()); 
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String[] items = SchoolSearch.getItems(Singleton.getInstance().getSchoolID()); 
		LblSchoolName.setText("School Name: " + items[0]);
		LblSchoolID.setText("ID: " + Singleton.getInstance().getSchoolID());
		LblCity.setText("City: " + items[1]);
		LblState.setText("State: " + items[2]);
		LblZip.setText("Zip Code: " + items[3]);
		LblHighest.setText("Highest Degree: " + items[4]);
		LblDominant.setText("Predominant Degree: " + items[5]); 
		LblInState.setText("In State: $" + items[6]);
		LblOutState.setText("Out State: $" + items[7]);
		LblOnCamp.setText("On Campus: $" + items[8]);
		LblOffCamp.setText("Off Campus: $" + items[9]);
		LblPopulation.setText("Student Population: " + items[10]);
		LblSAT.setText("Average SAT: " + items[11]);
		LblPellGrant.setText("Pell Grant Rate: " + items[12]);
		LblReligion.setText("Religion: " + items[13]);
		LblOwnership.setText("Ownership: " + items[14]);
		LblAdmission.setText("Admission Rate: " + items[15]);
		LblCompletion.setText("Completion Cohort: " + items[16]);
		LblRegion.setText("Region: " + items[17]);
		LblTestRequire.setText("Score Requirement: " + items[18]);
		LblLocale.setText("Locale: " + items[19]);
		TAMajors.setText(items[20]);
		
		if(Singleton.getInstance().isFavoriteSchool()) {
			LblFavorite.setVisible(true);
		}
	}
	
	public void SwapScene(ActionEvent actionevent, String resource, String SceneName){
		try {
			Parent secondRoot = FXMLLoader.load(getClass().getResource(resource));
			Scene secondScene = new Scene(secondRoot);
			Stage window = (Stage)((Node)actionevent.getSource()).getScene().getWindow();
			window.setScene(secondScene);
			window.setTitle(SceneName); 
			window.show();	
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
