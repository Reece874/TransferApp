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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.InfoDisplays;
import model.User;

public class EditController implements Initializable{
	
	@FXML
	private TextField TfName; 
	
	@FXML
	private TextField TfUsername; 
	
	@FXML
	private PasswordField TFPswd; 
	
	@FXML
	private PasswordField TFNewPswd; 
	
	@FXML
	private TextField TfSAT; 
	
	public void GoBack(ActionEvent actionevent) {
		SwapScene(actionevent, "/view/SchoolView.fxml", "College Search");
	}
	
	public void Update() {
		if(User.getInstance().updateAccount(TfUsername.getText(), TFPswd.getText(), TFNewPswd.getText(), TfName.getText(), TfSAT.getText())) {	
		InfoDisplays.displayGenericInformation("Account was Updated");
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TfUsername.setText(User.getInstance().getUsername());
		TfName.setText(User.getInstance().getFullName());
		TFPswd.setText(User.getInstance().getPassword());
		TFNewPswd.setText(User.getInstance().getPassword());
		TfSAT.setText(User.getInstance().getSAT());
		
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
