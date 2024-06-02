package controlleur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Ticket;

public class GestionTicketsPopUpControlleur {

    @FXML
    private Button valider;

    private Ticket ticket;
    private GestionTicketsControlleur gestionTicketsControlleur;

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void setGestionTicketsControlleur(GestionTicketsControlleur gestionTicketsControlleur) {
        this.gestionTicketsControlleur = gestionTicketsControlleur;
    }

    @FXML
    public void Valider() {
        gestionTicketsControlleur.deleteTicket(ticket);
        Stage stage = (Stage) valider.getScene().getWindow();
        stage.close();
    }
}
