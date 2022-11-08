package model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application{

	public static void main(String[] args) {
		Singleton.getInstance().getConnection();
		launch(args); 
		SchoolSearch.test();
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/view/SignIn.fxml"));
		Scene scene = new Scene(root); 
		stage.setScene(scene);
		stage.setTitle("Log in");
		stage.show();
		
	}

}
