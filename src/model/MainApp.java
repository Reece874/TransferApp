package model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * 
 * @author Reece Grimm
 * @version 12/1/2022
 *
 */
public class MainApp extends Application{
	/**
	 * Main Method
	 * @param args for Main Method
	 */
	public static void main(String[] args) {
		Singleton.getInstance().getConnection();
		launch(args); 
	}
	
	/**
	 * Launch Scene 
	 * @param stage for Scene
	 * @throws Exception for starting Scene
	 */
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/view/SignIn.fxml"));
		Scene scene = new Scene(root); 
		stage.setScene(scene);
		stage.setTitle("Log in");
		stage.show();
		
	}
	
	/**
	 * Close Program 
	 * @throws Exception for Closing Program
	 */
	public void stop() throws Exception{
		if(User.isUserSignedIn()) {
			User.getInstance().setFavs();
		}
		super.stop();
	}

}
