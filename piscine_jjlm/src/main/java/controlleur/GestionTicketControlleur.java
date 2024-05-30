package controlleur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GestionTicketControlleur {
	@FXML
	private Button retour;

	public void RetourMain() throws Exception {
		//FXMLLoader loaderAchat = new FXMLLoader(getClass().getResource("achat.fxml"));
		Parent rootMain = FXMLLoader.load(getClass().getClassLoader().getResource("vue/Main.fxml"));
		Stage stageMain = new Stage();

		Scene sceneMain = new Scene(rootMain, 600, 400);
		stageMain.setScene(sceneMain);
		stageMain.show();
		Stage currentStage = (Stage) retour.getScene().getWindow();
        currentStage.close();
	}
	
	public void Valider() throws Exception {
		//FXMLLoader loaderAchat = new FXMLLoader(getClass().getResource("achat.fxml"));
		Parent rootValide = FXMLLoader.load(getClass().getClassLoader().getResource("vue/Main.fxml"));
		Stage stageValide = new Stage();

		Scene sceneMain = new Scene(rootValide, 600, 400);
		stageValide.setScene(sceneMain);
		stageValide.show();
		Stage currentStage = (Stage) retour.getScene().getWindow();
        currentStage.close();
	}
}