package controlleur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdministrationControlleur {
	@FXML
	private Button retour;



	public void Retour() throws Exception {
		//FXMLLoader loaderAchat = new FXMLLoader(getClass().getResource("achat.fxml"));
		Parent rootRetour = FXMLLoader.load(getClass().getClassLoader().getResource("vue/authentification.fxml"));
		Stage stageRetour = new Stage();

		Scene sceneRetour = new Scene(rootRetour, 600, 400);
		stageRetour.setScene(sceneRetour);
		stageRetour.show();
		Stage currentStage = (Stage) retour.getScene().getWindow();
        currentStage.close();
		
	}
	
	public void GoToFormules() throws Exception {
		//FXMLLoader loaderAchat = new FXMLLoader(getClass().getResource("achat.fxml"));
		Parent rootFormule = FXMLLoader.load(getClass().getClassLoader().getResource("vue/gestionFormulesAdmin.fxml"));
		Stage stageFormule = new Stage();

		Scene sceneFormule = new Scene(rootFormule, 600, 400);
		stageFormule.setScene(sceneFormule);
		stageFormule.show();
		Stage currentStage = (Stage) retour.getScene().getWindow();
        currentStage.close();
		
	}
	
	public void GoToTickets() throws Exception {
		//FXMLLoader loaderAchat = new FXMLLoader(getClass().getResource("achat.fxml"));
		Parent rootTicket = FXMLLoader.load(getClass().getClassLoader().getResource("vue/gestionTicketsAdmin.fxml"));
		Stage stageTicket = new Stage();

		Scene sceneTicket = new Scene(rootTicket, 600, 400);
		stageTicket.setScene(sceneTicket);
		stageTicket.show();
		Stage currentStage = (Stage) retour.getScene().getWindow();
        currentStage.close();
		
	}
}
