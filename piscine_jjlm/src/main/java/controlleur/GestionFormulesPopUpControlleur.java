package controlleur;

import java.sql.SQLException;
import java.util.List;

import dao.databaseConnection;
import dao.piscine.PiscineDAO;
import dao.piscine.PiscineDAOImpl;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Formule;
import model.Piscine;
import model.Ticket;

public class GestionFormulesPopUpControlleur {

    @FXML
    private Button valider;

    private Formule formule;
    private GestionFormulesControlleur gestionFormulesControlleur;
    
    @FXML
    private TextField champsNom;
    
    
    @FXML
    private TextField champsPrix;

    @FXML
    private TextField champsDuree_validite;

    @FXML
    public ComboBox<Piscine> listPiscine;
    
    private PiscineDAOImpl piscineDAO = new PiscineDAOImpl();
    
    public void initialize() {
    	loadFormules();
   }

    public void setFormule(Formule formule) {
        this.formule = formule;
    }

    public void setGestionFormulesControlleur(GestionFormulesControlleur gestionFormulesControlleur) {
        this.gestionFormulesControlleur = gestionFormulesControlleur;
    }

    @FXML
    public void Valider() {
        gestionFormulesControlleur.addFormule(champsNom, champsPrix, champsDuree_validite, listPiscine);
        Stage stage = (Stage) valider.getScene().getWindow();
        stage.close();
    }
    
    public void loadFormules() {
        try {
            databaseConnection.closeConnectionNoArg();
            List<Piscine> piscine = piscineDAO.getAll();
            ObservableList<Piscine> piscineList = FXCollections.observableArrayList(piscine);
            listPiscine.setItems(piscineList); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
