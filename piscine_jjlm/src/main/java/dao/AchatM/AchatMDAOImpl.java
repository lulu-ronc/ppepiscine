package dao.AchatM;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.databaseConnection;
import model.AchatM;


public class AchatMDAOImpl implements AchatMDAO {

	@Override
	public AchatM read(int id_achat) throws SQLException {
		Connection con = databaseConnection.getInstance();
		AchatM achat = null;
		ResultSet rs =null;

		
		String sql = "SELECT * FROM Achat WHERE id_achat=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id_achat);
		rs = ps.executeQuery();
		

		if(rs.next()) {
			int oid = rs.getInt("id_achat");
			int id_piscine = rs.getInt("id_piscine");
			int montant = rs.getInt("montant");
			Date date_achat = rs.getDate("date_achat");
			achat = new AchatM(oid, id_piscine, montant, date_achat);
		}
		
		databaseConnection.closeResultSet(rs);
		databaseConnection.closePreparedStatement(ps);
		databaseConnection.closeConnection(con);

		return achat;
	}

	@Override
	public List<AchatM> getAll() throws SQLException {
		Connection con = databaseConnection.getInstance();
		String sql = "SELECT * FROM Achat";
		ResultSet rs = null;
		
		List <AchatM> listAchats = new ArrayList<>();
		Statement stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			int id = rs.getInt("id_achat");
			int id_piscine = rs.getInt("id_piscine");
			int montant = rs.getInt("montant");
			Date date_achat = rs.getDate("date_achat");
			
			AchatM achat = new AchatM(id, id_piscine, montant,date_achat);
			listAchats.add(achat);
		}
		
		databaseConnection.closeResultSet(rs);
		databaseConnection.closeStatement(stmt);
		databaseConnection.closeConnection(con);
		
		return listAchats;
	}

	@Override
	public int create(AchatM achat) throws SQLException {
		Connection con = databaseConnection.getInstance();
		String sql = "INSERT INTO Achat(id_achat,id_piscine,montant,date_achat) VALUES(?,?,?,?)";
		
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, achat.getId_achat());
		ps.setInt(2, achat.getId_piscine());
		ps.setInt(3, achat.getMontant());
		ps.setDate(4, achat.getDate_achat());
		
		int result = ps.executeUpdate();
		
		
		
		databaseConnection.closeStatement(ps);
		databaseConnection.closeConnection(con);
		
		if(result>0) {
			System.out.println("Insertion de "+achat.getId_achat() +" confirmé");
		}else {
			System.out.println("Echec de l'insertion");
		}
		
		return result;
	}

	@Override
	public int update(AchatM achat) throws SQLException {
		Connection con = databaseConnection.getInstance();
		String sql = "UPDATE Achat SET id_achat=?,id_piscine=?, montant=?,date_achat=? WHERE id_achat=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, achat.getId_achat());
		ps.setInt(2, achat.getId_piscine());
		ps.setInt(3, achat.getMontant());
		ps.setDate(4, achat.getDate_achat());
		
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
	public int delete(AchatM achat) throws SQLException {
		Connection con = databaseConnection.getInstance();
		String sql = "DELETE FROM Achat WHERE id_achat=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, achat.getId_achat());
		
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
