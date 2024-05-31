package controlleur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GestionFormulesControlleur {
	@FXML
	private Button retour;



	public void Retour() throws Exception {
		//FXMLLoader loaderAchat = new FXMLLoader(getClass().getResource("achat.fxml"));
		Parent rootRetour = FXMLLoader.load(getClass().getClassLoader().getResource("vue/Administration.fxml"));
		Stage stageRetour = new Stage();

		Scene sceneRetour = new Scene(rootRetour, 600, 400);
		stageRetour.setScene(sceneRetour);
		stageRetour.show();
		Stage currentStage = (Stage) retour.getScene().getWindow();
        currentStage.close();
		
	}
}
