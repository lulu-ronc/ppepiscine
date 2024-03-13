package dao.piscine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.databaseConnection;
import model.Piscine;

public class PiscineDAOImpl implements PiscineDAO {

	@Override
	public Piscine get(int id) throws SQLException {
		Connection con = databaseConnection.getInstance();
		Piscine piscine = null;
		ResultSet rs =null;

		
		String sql = "SELECT * FROM Piscine WHERE id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		

		if(rs.next()) {
			int oid = rs.getInt("id");
			String nom = rs.getString("nom");
			String adresse = rs.getString("adresse");
			int nbr_bassins = rs.getInt("nbr_bassins");
			piscine = new Piscine(oid,nom,adresse,nbr_bassins);
		}
		
		databaseConnection.closeResultSet(rs);
		databaseConnection.closePreparedStatement(ps);
		databaseConnection.closeConnection(con);

		return piscine;
	}

	@Override
	public List<Piscine> getAll() throws SQLException {
		Connection con = databaseConnection.getInstance();
		String sql = "SELECT * FROM Piscine";
		ResultSet rs = null;
		
		List <Piscine> listPiscines = new ArrayList<>();
		Statement stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			int id = rs.getInt("id");
			String nom = rs.getString("nom");
			String adresse = rs.getString("adresse");
			int nbr_bassins = rs.getInt("nbr_bassins");
			
			Piscine piscine = new Piscine(id, nom, adresse,nbr_bassins);
			listPiscines.add(piscine);
		}
		
		databaseConnection.closeResultSet(rs);
		databaseConnection.closeStatement(stmt);
		databaseConnection.closeConnection(con);
		
		return listPiscines;
	}

	@Override
	public int add(Piscine piscine) throws SQLException {
		Connection con = databaseConnection.getInstance();
		String sql = "INSERT INTO Piscine(id_piscine,nom,adresse,nbr_bassins) VALUES(?,?)";
		
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, piscine.getID());
		ps.setString(2, piscine.getNom());
		ps.setString(3, piscine.getAdresse());
		ps.setInt(4, piscine.getNbrBassin());
		
		int result = ps.executeUpdate();
		
		
		
		databaseConnection.closeStatement(ps);
		databaseConnection.closeConnection(con);
		
		if(result>0) {
			System.out.println("Insertion de "+piscine.getNom() +" confirmé");
		}else {
			System.out.println("Echec de l'insertion");
		}
		
		return result;
	}

	

	@Override
	public int update(Piscine piscine) throws SQLException {
		Connection con = databaseConnection.getInstance();
		String sql = "UPDATE Piscine SET id=?,nom=?, adresse=?,nbr_bassins=? WHERE id=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, piscine.getID());
		ps.setString(2, piscine.getNom());
		ps.setString(3, piscine.getAdresse());
		ps.setInt(4, piscine.getNbrBassin());
		
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
	public int delete(Piscine piscine) throws SQLException {
		Connection con = databaseConnection.getInstance();
		String sql = "DELETE FROM Piscine WHERE id=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, piscine.getID());
		
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
