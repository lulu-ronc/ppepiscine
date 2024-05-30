package controlleur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AvisVerificationControlleur {
    @FXML
    private Button RetourButton;
    
    public void RetourMain() throws Exception {
        Parent rootMain = FXMLLoader.load(getClass().getClassLoader().getResource("vue/Main.fxml"));
        Stage stageMain = new Stage();

        Scene sceneMain = new Scene(rootMain, 600, 400);
        stageMain.setScene(sceneMain);
        stageMain.show();
        
        Stage currentStage = (Stage) RetourButton.getScene().getWindow();
        currentStage.close();
    }
    
}