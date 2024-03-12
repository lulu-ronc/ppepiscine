package model;

import java.sql.Connection;

import dao.databaseConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	Connection con = new databaseConnection().Connect();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("vue/Main.fxml"));

			//Le conteneur principal de l'application(gère dimensions, style,..)
			Scene scene1 = new Scene(root, 600, 400);
			primaryStage.setScene(scene1);
			primaryStage.show();

		}catch(Exception e) {
			e.printStackTrace();
		} 
	}

//test


}


