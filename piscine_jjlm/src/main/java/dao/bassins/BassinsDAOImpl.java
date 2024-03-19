package dao.bassins;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.databaseConnection;
import model.Bassins;

public class BassinsDAOImpl implements BassinsDAO{

	@Override
	public Bassins get(int id_bassin) throws SQLException {
		Connection con = databaseConnection.getInstance();
		Bassins bassin = null;
		ResultSet rs =null;

		
		String sql = "SELECT * FROM Bassins WHERE id_bassin=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id_bassin);
		rs = ps.executeQuery();
		

		if(rs.next()) {
			int oid = rs.getInt("id_bassin");
			int id_piscine = rs.getInt("id_piscine");
			String nom = rs.getString("nom");
			int places = rs.getInt("places");
			bassin = new Bassins(oid, id_piscine, nom, places);
		}
		
		databaseConnection.closeResultSet(rs);
		databaseConnection.closePreparedStatement(ps);
		databaseConnection.closeConnection(con);

		return bassin;
	}

	@Override
	public List<Bassins> getAll() throws SQLException {
		Connection con = databaseConnection.getInstance();
		Bassins bassin = null;
		String sql = "SELECT * FROM Bassins";
		ResultSet rs = null;
		
		List <Bassins> listBassins = new ArrayList<>();
		Statement stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			int oid = rs.getInt("id_bassin");
			int id_piscine = rs.getInt("id_piscine");
			String nom = rs.getString("nom");
			int places = rs.getInt("places");
			bassin = new Bassins(oid, id_piscine, nom, places);;
			listBassins.add(bassin);
		}
		
		databaseConnection.closeResultSet(rs);
		databaseConnection.closeStatement(stmt);
		databaseConnection.closeConnection(con);
		
		return listBassins;
	}

	@Override
	public int add(Bassins bassin) throws SQLException {
		Connection con = databaseConnection.getInstance();
		String sql = "INSERT INTO Bassins(id_bassin,id_piscine,nom,places) VALUES(?,?,?,?)";
		
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, bassin.getId_bassin());
		ps.setInt(2, bassin.getId_piscine());
		ps.setString(3, bassin.getNom());
		ps.setInt(4, bassin.getPlaces());
		
		int result = ps.executeUpdate();
		
		
		
		databaseConnection.closeStatement(ps);
		databaseConnection.closeConnection(con);
		
		if(result>0) {
			System.out.println("Insertion de "+bassin.getId_bassin() +" confirmé");
		}else {
			System.out.println("Echec de l'insertion");
		}
		
		return result;
	}

	@Override
	public int update(Bassins bassin) throws SQLException {
		Connection con = databaseConnection.getInstance();
		String sql = "UPDATE Bassins SET id_bassin=?,id_piscine=?, nom=?,places=? WHERE id_bassin=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, bassin.getId_bassin());
		ps.setInt(2, bassin.getId_piscine());
		ps.setString(3, bassin.getNom());
		ps.setInt(4, bassin.getPlaces());
		
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
	public int delete(Bassins bassin) throws SQLException {
		Connection con = databaseConnection.getInstance();
		String sql = "DELETE FROM Bassins WHERE id_bassin=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, bassin.getId_bassin());
		
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
