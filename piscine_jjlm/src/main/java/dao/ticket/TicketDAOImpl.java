package dao.ticket;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.databaseConnection;

import model.Ticket;

public class TicketDAOImpl implements TicketDAO {

	@Override
	public Ticket read(int id_ticket) throws SQLException {
		Connection con = databaseConnection.getInstance();
		Ticket ticket = null;
		ResultSet rs =null;

		
		String sql = "SELECT * FROM Ticket WHERE id_ticket=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id_ticket);
		rs = ps.executeQuery();
		

		if(rs.next()) {
			int id = rs.getInt("id_ticket");
			int id_formule = rs.getInt("id_formule");
			int id_achat = rs.getInt("id_achat");
			Date date_debut = rs.getDate("date_debut");
			int quantite = rs.getInt("quantite");
			int code_validation = rs.getInt("code");
			ticket = new Ticket(id, id_formule, id_achat, date_debut, quantite, code_validation);
		}
		
		databaseConnection.closeResultSet(rs);
		databaseConnection.closePreparedStatement(ps);
		databaseConnection.closeConnection(con);

		return ticket;
	}

	@Override
	public List<Ticket> getAll() throws SQLException {
		Connection con = databaseConnection.getInstance();
		String sql = "SELECT * FROM Ticket";
		ResultSet rs = null;
		Ticket ticket=null;
		
		List <Ticket> listTickets = new ArrayList<>();
		Statement stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			int id = rs.getInt("id_ticket");
			int id_formule = rs.getInt("id_formule");
			int id_achat = rs.getInt("id_achat");
			Date date_debut = rs.getDate("date_debut");
			int quantite = rs.getInt("quantite");
			int code_validation = rs.getInt("code");
			ticket = new Ticket(id, id_formule, id_achat, date_debut, quantite, code_validation);;
			listTickets.add(ticket);
		}
		
		databaseConnection.closeResultSet(rs);
		databaseConnection.closeStatement(stmt);
		databaseConnection.closeConnection(con);
		
		return listTickets;
	}

	@Override
	public int create(Ticket ticket) throws SQLException {
		Connection con = databaseConnection.getInstance();
		String sql = "INSERT INTO Ticket(id_ticket,id_formule,id_achat,date_debut,quantite,code_validation) VALUES(?,?,?,?,?,?,?)";
		
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, ticket.getId_ticket());
		ps.setInt(2, ticket.getId_formule());
		ps.setInt(3, ticket.getId_achat());
		ps.setDate(4, ticket.getDate_debut());
		ps.setInt(4, ticket.getQuantite());
		ps.setInt(4, ticket.getCode_validation());
		int result = ps.executeUpdate();
		
		
		
		databaseConnection.closeStatement(ps);
		databaseConnection.closeConnection(con);
		
		if(result>0) {
			System.out.println("Insertion de "+ticket.getId_ticket() +" confirmé");
		}else {
			System.out.println("Echec de l'insertion");
		}
		
		return result;
	}

	@Override
	public int update(Ticket ticket) throws SQLException {
		Connection con = databaseConnection.getInstance();
		String sql = "UPDATE Ticket SET id_ticket=?,id_formule=?, id_achat=?,date_debut=?,quantite=?,code=? WHERE id_ticket=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, ticket.getId_ticket());
		ps.setInt(2, ticket.getId_formule());
		ps.setInt(3, ticket.getId_achat());
		ps.setDate(4, ticket.getDate_debut());
		ps.setInt(4, ticket.getQuantite());
		ps.setInt(4, ticket.getCode_validation());
		
		int result = ps.executeUpdate();
	
		databaseConnection.closeStatement(ps);
		databaseConnection.closeConnection(con);
		
		if(result>0) {
			System.out.println("Mise à jour confirmée");
		}else {
			System.out.println("Echec de la mise à jour");
		}
		
		return result;
	}

	@Override
	public int delete(Ticket ticket) throws SQLException {
		Connection con = databaseConnection.getInstance();
		String sql = "DELETE FROM Ticket WHERE id_ticket=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, ticket.getId_ticket());
		
		int result = ps.executeUpdate();
		
		databaseConnection.closeStatement(ps);
		databaseConnection.closeConnection(con);
		
		if(result>0) {
			System.out.println("Suppression confirmée");
		}else {
			System.out.println("Echec de la suppression");
		}
		
		return result;
	}

}
