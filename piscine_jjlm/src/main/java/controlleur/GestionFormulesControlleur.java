package controlleur;

import dao.databaseConnection;
import dao.formule.FormuleDAOImpl;
import dao.piscine.PiscineDAOImpl;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Formule;
import model.Piscine;
import model.Ticket;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GestionFormulesControlleur {
    @FXML
    private Button retour;
    
    @FXML
    private Button addFormule;

    @FXML
    private TableView<Formule> tableView;
    
    @FXML
    private TableColumn<Formule, String> idFormuleColumn;
    
    @FXML
    private TableColumn<Formule, Button> actionColumn;
    

    private FormuleDAOImpl formuleDAO = new FormuleDAOImpl();
    private PiscineDAOImpl piscineDAO = new PiscineDAOImpl();

    public void initialize() {
    	 idFormuleColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
         actionColumn.setCellValueFactory(param -> {
             ImageView deleteIcon = new ImageView("vue/image/Poubelle.PNG");
             deleteIcon.setFitHeight(16); // Ajustez la taille de l'image selon vos besoins
             deleteIcon.setFitWidth(16); // Ajustez la taille de l'image selon vos besoins
             
             Button deleteButton = new Button();
             deleteButton.setGraphic(deleteIcon);
             deleteButton.setOnAction(event -> showDeleteConfirmation(param.getValue()));
             addFormule.setOnAction(event -> showAddFormule(param.getValue()));
             deleteButton.setStyle("-fx-background-color: #5FFFF8;");
             
             return new SimpleObjectProperty<>(deleteButton);
         });

         loadFormules();
    }

    private void loadFormules() {
        try {
            databaseConnection.closeConnectionNoArg();
            List<Formule> formules = formuleDAO.getAll();
            ObservableList<Formule> formuleList = FXCollections.observableArrayList(formules);
            tableView.setItems(formuleList); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void showDeleteConfirmation(Formule formule) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/gestionFormulesAdminPopUpDelete.fxml"));
            Parent root = loader.load();

            // Pass the ticket to the pop-up controller
            DeleteGestionFormulesPopUpControlleur popUpController = loader.getController();
            popUpController.setFormule(formule);
            popUpController.setGestionFormulesControlleur(this);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteFormule(Formule formule) {
        try {
        	databaseConnection.closeConnectionNoArg();
            formuleDAO.delete(formule);
            loadFormules(); // Refresh the table after deletion
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    private void showAddFormule(Formule formule){
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/gestionFormulesAdminPopUpNouvelleFormule.fxml"));
            Parent root = loader.load();

            // Pass the ticket to the pop-up controller
            GestionFormulesPopUpControlleur popUpController = loader.getController();
            popUpController.setFormule(formule);
            popUpController.setGestionFormulesControlleur(this);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addFormule(TextField champsNom, TextField champsPrix, TextField champsDuree_validite, ComboBox<Piscine> listPiscine){
        try {
            String nom = champsNom.getText();
            int montant = Integer.parseInt(champsPrix.getText());
            int duree_validite = Integer.parseInt(champsDuree_validite.getText());
            Piscine piscineSelection = listPiscine.getSelectionModel().getSelectedItem();

            
            if (piscineSelection != null) {
                int idPiscine = piscineSelection.getID();
                
                // Récupérer le dernier ID utilisé
                int lastUsedId = formuleDAO.getLastUsedId();
                
                // Ajouter 1 pour obtenir le nouvel ID
                int newId = lastUsedId + 1;
                
                // Créer la nouvelle formule avec le nouvel ID
                Formule formule = new Formule(newId, idPiscine, duree_validite, nom, montant);
                databaseConnection.closeConnectionNoArg();

                formuleDAO.create(formule);
                System.out.println("Formule ajoutée avec succès !");
                loadFormules(); // Rafraîchir la table après l'ajout
            } else {
                System.out.println("Aucune piscine sélectionnée !");
            }
        } catch (NumberFormatException e) {
            System.out.println("Erreur de format des nombres: " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void Retour() throws IOException {
        Parent rootRetour = FXMLLoader.load(getClass().getClassLoader().getResource("vue/Administration.fxml"));
        Stage stageRetour = new Stage();

        Scene sceneRetour = new Scene(rootRetour, 600, 400);
        stageRetour.setScene(sceneRetour);
        stageRetour.show();
        Stage currentStage = (Stage) retour.getScene().getWindow();
        currentStage.close();
    }
}
