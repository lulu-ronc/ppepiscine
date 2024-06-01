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
	public Piscine read(int id_piscine) throws SQLException {
		Connection con = databaseConnection.getInstance();
		Piscine piscine = null;
		ResultSet rs =null;

		
		String sql = "SELECT * FROM Piscine WHERE id_piscine=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id_piscine);
		rs = ps.executeQuery();
		

		if(rs.next()) {
			int oid = rs.getInt("id_piscine");
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
			int id = rs.getInt("id_piscine");
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
	public int create(Piscine piscine) throws SQLException {
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
		String sql = "UPDATE Piscine SET id_piscine=?,nom=?, adresse=?,nbr_bassins=? WHERE id_piscine=?";
		
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
	
	public int getIdByName(String nom) throws SQLException {
		Connection con = databaseConnection.getInstance();
        String sql = "SELECT id_piscine FROM piscine WHERE nom = ?";
        String nom2;
        if(nom=="SaintGeorges") {
        	nom2= "Saint-Georges";
        }else {
        	nom2=nom;
        }
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, nom2);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id_piscine");
            }
        }
        return -1; // Indique que la piscine n'a pas été trouvée
    }




}
