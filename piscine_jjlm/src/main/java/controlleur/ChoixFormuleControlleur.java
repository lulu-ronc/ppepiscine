package controlleur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ChoixFormuleControlleur {
	@FXML
	private Button retour;

	public void RetourAchat() throws Exception {
		//FXMLLoader loaderAchat = new FXMLLoader(getClass().getResource("achat.fxml"));
		Parent rootAchat = FXMLLoader.load(getClass().getClassLoader().getResource("vue/achat.fxml"));
		Stage stageAchat = new Stage();

		Scene sceneAchat = new Scene(rootAchat, 600, 400);
		stageAchat.setScene(sceneAchat);
		stageAchat.show();
		Stage currentStage = (Stage) retour.getScene().getWindow();
        currentStage.close();
	}
	
	public void GoToPaiement() throws Exception {
		//FXMLLoader loaderAchat = new FXMLLoader(getClass().getResource("achat.fxml"));
		Parent rootPaiement = FXMLLoader.load(getClass().getClassLoader().getResource("vue/Paiement.fxml"));
		Stage stagePaiement = new Stage();

		Scene scenePaiement = new Scene(rootPaiement, 600, 400);
		stagePaiement.setScene(scenePaiement);
		stagePaiement.show();
		Stage currentStage = (Stage) retour.getScene().getWindow();
        currentStage.close();
	}
}
