package controlleur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Formule;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.PreparedStatement;
import dao.databaseConnection;
import dao.formule.FormuleDAOImpl;
import dao.piscine.PiscineDAOImpl;


public class ChoixFormuleControlleur {
	@FXML
	private Button retour;
	
	@FXML
    private TableView<Formule> listFormule;
    @FXML
    private TableColumn<Formule, String> colonneFormule;
    @FXML
    private TableColumn<Formule, Integer> colonneMontant;
    @FXML
    private TableColumn<Formule, Void> colonneMoins;
    @FXML
    private TableColumn<Formule, Void> colonnePlus;
    @FXML
    private TableColumn<Formule, Integer> colonneQuantite;
    
    @FXML
    private Label prixTotal;
    private String piscineName;
    
    ObservableList<Formule> formules = FXCollections.observableArrayList();
    
    private FormuleDAOImpl formuleDAO = new FormuleDAOImpl();
    private PiscineDAOImpl piscineDAO = new PiscineDAOImpl();
    private Map<Formule, SimpleIntegerProperty> quantites;
    
    @FXML
    public void initialize() {
        colonneFormule.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colonneMontant.setCellValueFactory(new PropertyValueFactory<>("Montant"));
        quantites = new HashMap<>();
        
    }

    public void setPiscineName(String piscineName) {
        this.piscineName = piscineName;
        loadFormules();
        configureButtonColumns();
        configureQuantiteColumn();
        updateTotal();
    }
    private void configureButtonColumns() {
        colonnePlus.setCellFactory(col -> createButtonCell(true));
        colonneMoins.setCellFactory(col -> createButtonCell(false));
    }

    private TableCell<Formule, Void> createButtonCell(boolean isPlus) {
        return new TableCell<Formule, Void>() {
        	private final Button button = new Button();
            private final ImageView imageView = new ImageView();

            {
            	imageView.setFitHeight(16);
                imageView.setFitWidth(16);
                button.setGraphic(imageView);
                button.setStyle("-fx-background-color: AFFFF8;");
            	
                button.setOnAction(event -> {
                	 Formule formule = getTableView().getItems().get(getIndex());
                     SimpleIntegerProperty quantite = quantites.get(formule);
                     if (isPlus) {
                         quantite.set(quantite.get() + 1);
                     } else {
                         if (quantite.get() > 0) {
                             quantite.set(quantite.get() - 1);
                         }
                     }
                     updateTotal();
                     getTableView().refresh();
                 });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                    setGraphic(null);
                } else {
                	if (isPlus) {
                        imageView.setImage(new Image(getClass().getResourceAsStream("/vue/image/plus.png")));
                    } else {
                        imageView.setImage(new Image(getClass().getResourceAsStream("/vue/image/moins.png")));
                    }
                    setGraphic(button);
                }
            }
        };
    }
    
   
    private void configureQuantiteColumn() {
        colonneQuantite.setCellValueFactory(cellData -> {
            Formule formule = cellData.getValue();
            if (!quantites.containsKey(formule)) {
                quantites.put(formule, new SimpleIntegerProperty(0));
            }
            return quantites.get(formule).asObject();
        });
    }
   

    private void loadFormules() {
        
        try {
        	int piscineId = piscineDAO.getIdByName(piscineName);
        	if (piscineId != -1) {
            System.out.println("Loading formules using DAO...");
            List<Formule> formuleList = formuleDAO.getFormulesByPiscineId(piscineId);
            //List<Formule> formuleList = formuleDAO.getAll();
            formules.addAll(formuleList);
            
            System.out.println("Formules loaded successfully.");
            
            
        	}
        } catch (SQLException e) {
            System.err.println("SQLException occurred while loading formules using DAO: " + e.getMessage());
            e.printStackTrace();
        }

        listFormule.setItems(formules);
    }
    
    private void updateTotal() {
    	
        int total = 0;
        for (Formule formule : formules) {
            int quantite = quantites.getOrDefault(formule, new SimpleIntegerProperty(0)).get();
            total += formule.getMontant() * quantite;
        }
        prixTotal.setText(String.valueOf(total) + "€");
    }

	public void RetourAchat() throws Exception {
		//FXMLLoader loaderAchat = new FXMLLoader(getClass().getResource("achat.fxml"));
		Parent rootAchat = FXMLLoader.load(getClass().getClassLoader().getResource("vue/achat.fxml"));
		Stage stageAchat = new Stage();

		Scene sceneAchat = new Scene(rootAchat, 600, 400);
		stageAchat.setScene(sceneAchat);
		stageAchat.show();
		Stage currentStage = (Stage) retour.getScene().getWindow();
        currentStage.close();
	}
	
	public void GoToPaiement() throws Exception {
		//FXMLLoader loaderAchat = new FXMLLoader(getClass().getResource("achat.fxml"));
		Parent rootPaiement = FXMLLoader.load(getClass().getClassLoader().getResource("vue/Paiement.fxml"));
		Stage stagePaiement = new Stage();

		Scene scenePaiement = new Scene(rootPaiement, 600, 400);
		stagePaiement.setScene(scenePaiement);
		stagePaiement.show();
		Stage currentStage = (Stage) retour.getScene().getWindow();
        currentStage.close();
	}
}
