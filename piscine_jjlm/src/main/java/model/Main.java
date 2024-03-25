package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.databaseConnection;
import dao.piscine.PiscineDAO;
import dao.piscine.PiscineDAOImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = databaseConnection.getInstance();
		PiscineDAO piscineDAO = new PiscineDAOImpl();
		Piscine piscine = piscineDAO.get(1);
		System.out.println(piscine);
		
		
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


