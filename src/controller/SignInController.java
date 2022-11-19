package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.InfoDisplays;
import model.UserSearch;

public class SignInController{
	
	@FXML
	private TextField tfUser; 
	
	@FXML
	private PasswordField tfPswd; 
	
	public void SignIn(ActionEvent actionevent) {
		if(UserSearch.login(tfUser.getText(), tfPswd.getText())) {
			SwapScene(actionevent, "/view/SchoolView.fxml", "College Search");
		}else {
			InfoDisplays.displayGenericError("Username or Password is incorrect", "If you do not have an account, please sign up");
		}
	}
	
	public void BtnSignUp(ActionEvent actionevent) {
		SwapScene(actionevent, "/view/SignUp.fxml", "Sign Up");
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
