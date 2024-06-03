package controlleur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Formule;

public class DeleteGestionFormulesPopUpControlleur {

    @FXML
    private Button valider;

    private Formule formule;
    private GestionFormulesControlleur gestionFormulesControlleur;

    public void setFormule(Formule formule) {
        this.formule = formule;
    }

    public void setGestionFormulesControlleur(GestionFormulesControlleur gestionFormulesControlleur) {
        this.gestionFormulesControlleur = gestionFormulesControlleur;
    }

    @FXML
    public void Valider() {
        gestionFormulesControlleur.deleteFormule(formule);
        Stage stage = (Stage) valider.getScene().getWindow();
        stage.close();
    }
}

