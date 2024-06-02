package controlleur;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


import dao.databaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class AdminAuthControlleur implements Initializable{
	@FXML private Button retour;
	@FXML private TextField txtLogin;
	@FXML private PasswordField txtPassword;
	@FXML private Label labelEtat;

	public void login(ActionEvent event) throws SQLException, IOException, NoSuchAlgorithmException {
	    Connection con = databaseConnection.Connect();
	    PreparedStatement stat = null;
	    ResultSet rs = null;
	    String sql = "SELECT * FROM Admin WHERE login = ? AND mdp = ?";
	    try {
	        stat = con.prepareStatement(sql);

	        // Hash the password using SHA-256
	        String hashedPassword = hashPassword(txtPassword.getText().toString());
	        
	        stat.setString(1, txtLogin.getText().toString());
	        stat.setString(2, hashedPassword);
	        rs = stat.executeQuery();
	        if(rs.next()) {
	            labelEtat.setText("Connecté!");
	            Parent rootConnexion = FXMLLoader.load(getClass().getClassLoader().getResource("vue/Administration.fxml"));
	            Stage stageConnexion = new Stage();

	            Scene sceneConnexion = new Scene(rootConnexion, 600, 400);
	            stageConnexion.setScene(sceneConnexion);
	            stageConnexion.show();
	            Stage currentStage = (Stage) retour.getScene().getWindow();
	            currentStage.close();
	        } else {
	            labelEtat.setText("Erreur Connection!");
	        }
	    } catch (SQLException e) {
	        // Handle SQL Exception
	    } finally {
	        // Close resources
	        if (rs != null) rs.close();
	        if (stat != null) stat.close();
	        if (con != null) con.close();
	    }
	}

	// Method to hash password using SHA-256
	private String hashPassword(String password) throws NoSuchAlgorithmException {
	    MessageDigest digest = MessageDigest.getInstance("SHA-256");
	    byte[] encodedhash = digest.digest(password.getBytes());
	    return bytesToHex(encodedhash);
	}

	// Convert byte array to hexadecimal string
	private String bytesToHex(byte[] hash) {
	    StringBuilder hexString = new StringBuilder(2 * hash.length);
	    for (byte b : hash) {
	        String hex = Integer.toHexString(0xff & b);
	        if (hex.length() == 1) {
	            hexString.append('0');
	        }
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}

	public void Retour() throws Exception {
		//FXMLLoader loaderAchat = new FXMLLoader(getClass().getResource("achat.fxml"));
		Parent rootRetour = FXMLLoader.load(getClass().getClassLoader().getResource("vue/Main.fxml"));
		Stage stageRetour = new Stage();

		Scene sceneRetour = new Scene(rootRetour, 600, 400);
		stageRetour.setScene(sceneRetour);
		stageRetour.show();
		Stage currentStage = (Stage) retour.getScene().getWindow();
        currentStage.close();
		
	}
	
	public void Connexion() throws Exception {
		//FXMLLoader loaderAchat = new FXMLLoader(getClass().getResource("achat.fxml"));
		Parent rootConnexion = FXMLLoader.load(getClass().getClassLoader().getResource("vue/Administration.fxml"));
		Stage stageConnexion = new Stage();

		Scene sceneConnexion = new Scene(rootConnexion, 600, 400);
		stageConnexion.setScene(sceneConnexion);
		stageConnexion.show();
		Stage currentStage = (Stage) retour.getScene().getWindow();
        currentStage.close();
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
