package controlleur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AchatControlleur {
	@FXML
	private Button retour;

	@FXML
	private Button Brequigny;
	@FXML
	private Button Gayeulles;
	@FXML
	private Button Villejean;
	@FXML
	private Button SaintGeorges;
	
	 @FXML
	    public void initialize() {
	
	    }

	  @FXML
	    public void GoToChoixBrequigny() throws Exception {
	        GoToChoix("Brequigny");
	    }

	    @FXML
	    public void GoToChoixGayeulles() throws Exception {
	        GoToChoix("Gayeulles");
	    }

	    @FXML
	    public void GoToChoixVillejean() throws Exception {
	        GoToChoix("Villejean");
	    }

	    @FXML
	    public void GoToChoixSaintGeorges() throws Exception {
	        GoToChoix("SaintGeorges");
	    }
	public void RetourMain() throws Exception {
		//FXMLLoader loaderAchat = new FXMLLoader(getClass().getResource("achat.fxml"));
		//Parent rootMain = FXMLLoader.load(getClass().getClassLoader().getResource("vue/Main.fxml"));
		Parent rootMain = FXMLLoader.load(getClass().getClassLoader().getResource("vue/Main.fxml"));
		Stage stageMain = new Stage();

		Scene sceneMain = new Scene(rootMain, 600, 400);
		stageMain.setScene(sceneMain);
		stageMain.show();
		Stage currentStage = (Stage) retour.getScene().getWindow();
		currentStage.close();
	}
	
	

	public void GoToChoix(String piscineName) throws Exception {
		//FXMLLoader loaderAchat = new FXMLLoader(getClass().getResource("achat.fxml"));
		//Parent rootChoix = FXMLLoader.load(getClass().getClassLoader().getResource("vue/choixFormule.fxml"));
		
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("vue/choixFormule.fxml"));
        Parent rootChoix = loader.load();
		Stage stageChoix = new Stage();
		
		ChoixFormuleControlleur controller = loader.getController();
        controller.setPiscineName(piscineName);

		Scene sceneMain = new Scene(rootChoix, 600, 400);
		stageChoix.setScene(sceneMain);
		stageChoix.show();
		Stage currentStage = (Stage) retour.getScene().getWindow();
		currentStage.close();
	}
}
