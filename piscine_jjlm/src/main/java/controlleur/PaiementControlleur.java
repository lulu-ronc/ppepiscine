package controlleur;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;

public class PaiementControlleur implements Initializable {
	@FXML
	private Button retour;
	
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Attendre 5 secondes
        new Thread(() -> {
            try {
                Thread.sleep(5000); // Attendre 5 secondes

                // Charger Main.fxml après l'attente
                Platform.runLater(() -> {
                    try {
                    	Parent rootMain = FXMLLoader.load(getClass().getClassLoader().getResource("vue/Main.fxml"));
                		Stage stageMain = new Stage();

                		Scene sceneMain = new Scene(rootMain, 600, 400);
                		stageMain.setScene(sceneMain);
                		stageMain.show();
                		Stage currentStage = (Stage) retour.getScene().getWindow();
                        currentStage.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
