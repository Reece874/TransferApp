package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import model.ComboLists;
import model.InfoDisplays;
import model.SchoolSearch;
import model.Singleton;
import model.TableItem;
import model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SchoolController implements Initializable{
		
	@FXML
	private TableView<TableItem> SchoolTable; 
	
	@FXML 
	private TableColumn<TableItem, String> ColID;
	
	@FXML 
	private TableColumn<TableItem, String> ColName;
	
	@FXML 
	private TableColumn<TableItem, String> ColCity;
	
	@FXML 
	private TableColumn<TableItem, String> ColState;
	
	@FXML
	private TextField TFSchoolName; 
	
	@FXML
	private TextField TFID; 
	
	@FXML
	private TextField TFCity;
	
	@FXML
	private TextField TFMin; 
	
	@FXML
	private TextField TFMax; 
	
	@FXML
	private TextField TFSAT; 
	
	@FXML
	private Button Search;
	
	@FXML
	private Button BtnUseSAT; 
	
	@FXML 
	private ComboBox<String> ComboState; 
	
	@FXML 
	private ComboBox<String> ComboHighest;
	
	@FXML 
	private ComboBox<String> ComboPredominant; 
	
	@FXML 
	private ComboBox<String> ComboMajor; 
	
	@FXML
	private ComboBox<String> ComboReligious; 
	
	@FXML
	private ComboBox<String> ComboPrivateOrPublic; 
	
	@FXML
	private ComboBox<String> ComboLocale; 
	
	@FXML
	private ComboBox<String> ComboRegion; 
	
	@FXML
	private CheckBox CheckState; 
	
	@FXML
	private CheckBox CheckOffCampus; 
	
	@FXML
	private CheckBox CheckAssoc; 
	
	@FXML
	private CheckBox CheckBachelor; 
	
	@FXML
	private CheckBox CheckNotRequired; 
	
	@FXML
	private Label LblHi; 
	
	@FXML
	private Spinner<Integer> SpinnerTuition; 
	
	@FXML
	private Spinner<Integer> SpinnerRoomBoard; 
	
	@FXML
	private Spinner<Integer> SpinnerPellGrant; 
	
	@FXML
	private Spinner<Integer> SpinnerAdmissions; 

	
	public void GetFavs() {
		SchoolTable.setItems(SchoolSearch.getFavorites(User.getInstance().getFavs()));
	}
	
	
	public void SearchParams() {
		SchoolTable.setItems(SchoolSearch.searchParams(TFID.getText(), 
												  TFSchoolName.getText(), 
												  TFCity.getText(),
												  ComboState.getValue(),
												  ComboHighest.getValue(),
												  ComboPredominant.getValue(),
												  SpinnerTuition.getValue(),
												  SpinnerRoomBoard.getValue(),
												  TFMin.getText(),
												  TFMax.getText(),
												  ComboMajor.getValue(),
												  TFSAT.getText(),
												  ComboReligious.getValue(),
												  ComboPrivateOrPublic.getValue(),
												  SpinnerPellGrant.getValue(),
												  SpinnerAdmissions.getValue(),
												  ComboRegion.getValue(),
												  ComboLocale.getValue()));
		
	}
	
	public void ChangeInOrOut() {
		Singleton.getInstance().setOutOfState(CheckState.isSelected());
	}
	
	public void ChangeOnOrOff() {
		Singleton.getInstance().setOffCampus(CheckOffCampus.isSelected());
	}
	
	public void ChangeAssoc() {
		Singleton.getInstance().setAssociates(CheckAssoc.isSelected());
	}
	
	public void ChangeBachelor() {
		Singleton.getInstance().setBachelors(CheckBachelor.isSelected());
	}
	
	public void ChangeRequired() {
		Singleton.getInstance().setRequirement(CheckNotRequired.isSelected());
	}
	
	public void UseMyScore() {
		CheckNotRequired.setSelected(false);
		CheckNotRequired.setDisable(!CheckNotRequired.isDisable());	
		//TFSAT.setText((TFSAT.isDisable())? "" : Singleton.getInstance().getSAT());
		TFSAT.setText((TFSAT.isDisable())? "" : User.getInstance().getSAT());
		TFSAT.setDisable(!TFSAT.isDisable());
	}
	
	public void ExpandSchool(ActionEvent actionevent) {
		if(SchoolTable.getSelectionModel().getSelectedItem() != null) {
			Singleton.getInstance().setSchoolID(SchoolTable.getSelectionModel().getSelectedItem().getID());
			Singleton.getInstance().resetAll();
			SwapScene(actionevent, "/view/ExpandedView.fxml", "School Info");
		}else {
			InfoDisplays.displayGenericInformation("No School Is Selected");
		}
	}
	
	public void goToEdit(ActionEvent actionevent) {
		SwapScene(actionevent, "/view/EditView.fxml", "Account Info");
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
		SpinnerTuition.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100000, 0, 500));
		SpinnerRoomBoard.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100000, 0, 500));
		SpinnerPellGrant.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0, 5));
		SpinnerAdmissions.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0, 5));
		
		ComboState.setItems(ComboLists.getStates());
		ComboHighest.setItems(ComboLists.getDegrees());
		ComboPredominant.setItems(ComboLists.getDegrees());
		ComboMajor.setItems(ComboLists.getMajors());
		ComboReligious.setItems(ComboLists.getReligions());
		ComboPrivateOrPublic.setItems(ComboLists.getOwnership());
		ComboLocale.setItems(ComboLists.getLocale());
		ComboRegion.setItems(ComboLists.getRegion());
		
		ColID.setCellValueFactory(new PropertyValueFactory<>("ID"));
		ColName.setCellValueFactory(new PropertyValueFactory<>("SchoolName"));
		ColCity.setCellValueFactory(new PropertyValueFactory<>("City"));
		ColState.setCellValueFactory(new PropertyValueFactory<>("State"));
		
		LblHi.setText("Hello, " + User.getInstance().getFullName());
		BtnUseSAT.setDisable((User.getInstance().getSAT().equals("0")? true : false)); 
		SchoolTable.setItems(SchoolSearch.getAll());		
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
