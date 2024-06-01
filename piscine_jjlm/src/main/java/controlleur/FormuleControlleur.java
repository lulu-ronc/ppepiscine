package controlleur;

import javafx.fxml.FXML;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

public class FormuleControlleur {

    @FXML
    private TableView<Formule> listFormule;
    @FXML
    private TableColumn<Formule, String> colonneNom;
    @FXML
    private TableColumn<Formule, Integer> colonneMontant;
    @FXML
    private TableColumn<Formule, Void> colonneMoins;
    @FXML
    private TableColumn<Formule, Void> colonnePlus;
    @FXML
    private TableColumn<Formule, Integer> colonneQuantite;
    
    private Label totalFormule;
    private String piscineName;
    private  ObservableList<Formule> formules;

    private FormuleDAOImpl formuleDAO = new FormuleDAOImpl();
    private Map<Formule, SimpleIntegerProperty> quantites;
    
    @FXML
    public void initialize() {
        colonneNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
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
            private final Button button = new Button(isPlus ? "+" : "-");

            {
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
                     getTableView().refresh();
                 });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                    setGraphic(null);
                } else {
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
        ObservableList<Formule> formules = FXCollections.observableArrayList();
        try {
            System.out.println("Loading formules using DAO...");
            List<Formule> formuleList = formuleDAO.getAll();
            formules.addAll(formuleList);
            
            System.out.println("Formules loaded successfully.");
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
        totalFormule.setText(String.valueOf(total));
    }
}