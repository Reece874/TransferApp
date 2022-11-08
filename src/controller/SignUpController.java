package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.InfoDisplays;
import model.UserSearch;

public class SignUpController {
	
	@FXML
	private TextField tfUser; 
	
	@FXML
	private TextField tfPswd; 
	
	@FXML
	private TextField tfFirst;
	
	@FXML
	private TextField tfLast;
	
	@FXML
	private TextField tfSAT;
	
	@FXML
	private TextField tfPswdconf;
	
	public void SignUp(ActionEvent actionevent) {
		if(UserSearch.SignUp(tfUser.getText(), tfPswd.getText(), tfPswdconf.getText(), tfFirst.getText(), tfLast.getText(), tfSAT.getText())) {
			SwapScene(actionevent, "/view/SignIn.fxml", "Sign In");
		}
	}
	
	public void GoBack(ActionEvent actionevent) {
		SwapScene(actionevent, "/view/SignIn.fxml", "Sign In");
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
