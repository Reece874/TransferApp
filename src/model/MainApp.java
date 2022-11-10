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
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/view/SignIn.fxml"));
		Scene scene = new Scene(root); 
		stage.setScene(scene);
		stage.setTitle("Log in");
		stage.show();
		
	}
	
	public void stop() throws Exception{
		if(Singleton.getInstance().isUserSignedIn()) {
			Singleton.getInstance().setFavs();
		}
		super.stop();
	}

}
