package controlleur;

import java.sql.SQLException;
import java.util.List;

import dao.databaseConnection;
import dao.formule.FormuleDAOImpl;
import dao.piscine.PiscineDAOImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Formule;
import model.Piscine;

public class GestionFormulesControlleur {
	@FXML
	private Button retour;

	@FXML
	private TextField champsId_piscine;

	@FXML
	private TextField champsNom;

	@FXML
	private TextField champsMontant;

	@FXML
	private TextField champsDuree_validite;

	@FXML
	private ComboBox<Piscine> listPiscine;
	
	private Button valider1;

	private FormuleDAOImpl formuleDAO = new FormuleDAOImpl();
	private PiscineDAOImpl piscineDAO = new PiscineDAOImpl();


public void initialize() {
	try {
		List<Piscine> listPiscines= piscineDAO.getAll();
		listPiscine.getItems().addAll(listPiscines);
		
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
}
	

@FXML
public void addFormule() {
    try {
        String nom = champsNom.getText();
        int montant = Integer.parseInt(champsMontant.getText());
        int duree_validite = Integer.parseInt(champsDuree_validite.getText());
        Piscine piscineSelection = listPiscine.getSelectionModel().getSelectedItem();

        if (piscineSelection != null) {
            int idPiscine = piscineSelection.getID();
            Formule formule = new Formule(0,idPiscine, duree_validite, nom, montant);
            databaseConnection.closeConnectionNoArg();

            FormuleDAOImpl formuleDAO = new FormuleDAOImpl();

            formuleDAO.create(formule);
            System.out.println("Formule ajoutée avec succès !");
        } else {
            System.out.println("Aucune piscine sélectionnée !");
        }
    } catch (NumberFormatException e) {
        System.out.println("Erreur de format des nombres: " + e.getMessage());
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

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
