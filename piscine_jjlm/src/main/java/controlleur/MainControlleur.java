package controlleur;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainControlleur {
	@FXML
	private Button achat;



	public void goToAchat() throws Exception {
		//FXMLLoader loaderAchat = new FXMLLoader(getClass().getResource("achat.fxml"));
		Parent rootAchat = FXMLLoader.load(getClass().getClassLoader().getResource("vue/achat.fxml"));
		Stage stageAchat = new Stage();

		Scene sceneAchat = new Scene(rootAchat, 600, 400);
		stageAchat.setScene(sceneAchat);
		stageAchat.show();
		Stage currentStage = (Stage) achat.getScene().getWindow();
        currentStage.close();
		
	}
	
	public void goToVerifier() throws Exception {
		//FXMLLoader loaderAchat = new FXMLLoader(getClass().getResource("achat.fxml"));
		Parent rootVerifier = FXMLLoader.load(getClass().getClassLoader().getResource("vue/verifier.fxml"));
		Stage stageVerifier = new Stage();

		Scene sceneVerifier = new Scene(rootVerifier, 600, 400);
		stageVerifier.setScene(sceneVerifier);
		stageVerifier.show();
		Stage currentStage = (Stage) achat.getScene().getWindow();
        currentStage.close();
	}
	
	
	public void goToModifier() throws Exception {
		//FXMLLoader loaderAchat = new FXMLLoader(getClass().getResource("achat.fxml"));
		Parent rootModifier = FXMLLoader.load(getClass().getClassLoader().getResource("vue/ScanModifTicket.fxml"));
		Stage stageModifier = new Stage();

		Scene sceneModifier = new Scene(rootModifier, 600, 400);
		stageModifier.setScene(sceneModifier);
		stageModifier.show();
		Stage currentStage = (Stage) achat.getScene().getWindow();
        currentStage.close();
	}
	
	public void goToAdmin() throws Exception {
		//FXMLLoader loaderAchat = new FXMLLoader(getClass().getResource("achat.fxml"));
		Parent rootAdmin = FXMLLoader.load(getClass().getClassLoader().getResource("vue/authentification.fxml"));
		Stage stageAdmin = new Stage();

		Scene sceneAdmin = new Scene(rootAdmin, 600, 400);
		stageAdmin.setScene(sceneAdmin);
		stageAdmin.show();
		Stage currentStage = (Stage) achat.getScene().getWindow();
        currentStage.close();
	}
}
