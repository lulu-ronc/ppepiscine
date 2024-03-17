package dao.professeur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.databaseConnection;
import model.Professeur;

public class ProfesseurDAOImpl implements ProfesseurDAO {

	@Override
	public Professeur get(int id) throws SQLException {
		Connection con = databaseConnection.getInstance();
		Professeur professeur = null;
		ResultSet rs =null;
		
		String sql ="SELECT * FROM Professeur WHERE id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1,id);
		rs=ps.executeQuery();
		
		if(rs.next()) {
			int oid = rs.getInt("id");
			String nom = rs.getString("nom");
			boolean detente = rs.getBoolean("detente");
			boolean competitif = rs.getBoolean("competitif");
			boolean surveillance_bassin = rs.getBoolean("surveillance_bassin");
			int ID_piscine = rs.getInt("ID_piscine");
			professeur =new Professeur(oid, nom, detente, competitif, surveillance_bassin, ID_piscine);
		}
		
		
		databaseConnection.closeResultSet(rs);
		databaseConnection.closePreparedStatement(ps);
		databaseConnection.closeConnection(con);
		return professeur;
	}

	@Override
	public List<Professeur> getAll() throws SQLException {
		Connection con = databaseConnection.getInstance();
		List<Professeur> listProfesseurs = new ArrayList<>();
		Professeur professeur = null;
		ResultSet rs =null;
		String sql ="SELECT * FROM Professeur";
		
		Statement stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			int oid = rs.getInt("id");
			String nom = rs.getString("nom");
			boolean detente = rs.getBoolean("detente");
			boolean competitif = rs.getBoolean("competitif");
			boolean surveillance_bassin = rs.getBoolean("surveillance_bassin");
			int ID_piscine = rs.getInt("ID_piscine");
			professeur =new Professeur(oid, nom, detente, competitif, surveillance_bassin, ID_piscine);
			listProfesseurs.add(professeur);
		}
		
		databaseConnection.closeResultSet(rs);
		databaseConnection.closeStatement(stmt);
		databaseConnection.closeConnection(con);
		return listProfesseurs;
	}

	@Override
	public int add(Professeur professeur) throws SQLException {
		Connection con = databaseConnection.getInstance();
		String sql = "INSERT INTO Formule(id,nom,detente,competitif,surveillance_bassin,ID_piscine) VALUES(?,?,?,?,?,?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1,professeur.getID());
		ps.setString(2,professeur.getNom());
		ps.setBoolean(3,professeur.getDetente());
		ps.setBoolean(4,professeur.getCompetitif());
		ps.setBoolean(5,professeur.getSurveillanceBassin());
		ps.setInt(6,professeur.getID_piscine());
		
		int result = ps.executeUpdate();
		
		databaseConnection.closePreparedStatement(ps);
		databaseConnection.closeConnection(con);
		
		if(result>0) {
			System.out.println("Insertion de "+ professeur.getNom() +" confirmé");
		}else {
			System.out.println("Echec de l'insertion");
		}
		return result;
	}

	@Override
	public int update(Professeur professeur) throws SQLException {
		Connection con = databaseConnection.getInstance();
		String sql = "UPDATE Professeur SET id=?,nom=?, detente=?,competitif=? surveillance_bassin=?,ID_piscine=? WHERE id=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1,professeur.getID());
		ps.setString(2,professeur.getNom());
		ps.setBoolean(3,professeur.getDetente());
		ps.setBoolean(4,professeur.getCompetitif());
		ps.setBoolean(5,professeur.getSurveillanceBassin());
		ps.setInt(6,professeur.getID_piscine());
		
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
	public int delete(Professeur professeur) throws SQLException {
		Connection con = databaseConnection.getInstance();
		String sql = "DELETE FROM Professeur WHERE id=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, professeur.getID());
		
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
