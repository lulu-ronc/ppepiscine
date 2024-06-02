package controlleur;

import dao.databaseConnection;
import dao.ticket.TicketDAO;
import dao.ticket.TicketDAOImpl;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Ticket;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GestionTicketsControlleur {

    @FXML
    private Button retour;

    @FXML
    private TableView<Ticket> tableView;

    @FXML
    private TableColumn<Ticket, Integer> idTicketColumn;

    @FXML
    private TableColumn<Ticket, Button> actionColumn;

    private TicketDAO ticketDAO = new TicketDAOImpl();

    @FXML
    public void initialize() {
        idTicketColumn.setCellValueFactory(new PropertyValueFactory<>("id_ticket"));
        actionColumn.setCellValueFactory(param -> {
            Button deleteButton = new Button("Supprimer");
            deleteButton.setOnAction(event -> showDeleteConfirmation(param.getValue()));
            return new SimpleObjectProperty<>(deleteButton);
        });

        loadTickets();
    }

    private void loadTickets() {
        try {
            List<Ticket> tickets = ticketDAO.getAll();
            ObservableList<Ticket> ticketList = FXCollections.observableArrayList(tickets);
            tableView.setItems(ticketList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showDeleteConfirmation(Ticket ticket) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/gestionTicketsAdminPopUpDelete.fxml"));
            Parent root = loader.load();

            // Pass the ticket to the pop-up controller
            GestionTicketsPopUpControlleur popUpController = loader.getController();
            popUpController.setTicket(ticket);
            popUpController.setGestionTicketsControlleur(this);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteTicket(Ticket ticket) {
        try {
        	databaseConnection.closeConnectionNoArg();
            ticketDAO.delete(ticket);
            loadTickets(); // Refresh the table after deletion
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Retour() throws Exception {
        Parent rootRetour = FXMLLoader.load(getClass().getClassLoader().getResource("vue/Administration.fxml"));
        Stage stageRetour = new Stage();

        Scene sceneRetour = new Scene(rootRetour, 600, 400);
        stageRetour.setScene(sceneRetour);
        stageRetour.show();
        Stage currentStage = (Stage) retour.getScene().getWindow();
        currentStage.close();
    }
}
