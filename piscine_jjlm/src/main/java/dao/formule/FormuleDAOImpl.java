package dao.formule;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import dao.databaseConnection;
import model.Formule;
import model.Piscine;


public class FormuleDAOImpl implements FormuleDAO {

	@Override
	public Formule read(int id) throws SQLException {
		Connection con = databaseConnection.getInstance();
		Formule formule = null;
		ResultSet rs =null;
		
		String sql ="SELECT * FROM Formule WHERE id_formule=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1,id);
		rs=ps.executeQuery();
		
		if(rs.next()) {
			int oid = rs.getInt("id_formule");
			String nom = rs.getString("nom");
			int id_piscine = rs.getInt("id_piscine");
			int duree_validite = rs.getInt("duree_validite");
			int montant = rs.getInt("montant");
			formule =new Formule(oid, duree_validite, id_piscine, nom, montant);
		}
		
		
		databaseConnection.closeResultSet(rs);
		databaseConnection.closePreparedStatement(ps);
		databaseConnection.closeConnection(con);
		
		return formule;	
	}

	@Override
	public List<Formule> getAll() throws SQLException {
		Connection con = databaseConnection.getInstance();
		List<Formule> listFormules = new ArrayList<>();
		Formule formule = null;
		ResultSet rs =null;
		String sql ="SELECT * FROM Formule";
		
		Statement stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			int oid = rs.getInt("id_formule");
			String nom = rs.getString("nom");
			int id_piscine = rs.getInt("id_piscine");
			int duree_validite = rs.getInt("duree_validite");
			int montant = rs.getInt("montant");
			formule =new Formule(oid, duree_validite, id_piscine, nom, montant);
			listFormules.add(formule);
		}
		
		databaseConnection.closeResultSet(rs);
		databaseConnection.closeStatement(stmt);
		databaseConnection.closeConnection(con);
		
		return listFormules;
	}

	@Override
	public int create(Formule formule) throws SQLException {
		Connection con = databaseConnection.getInstance();
		String sql = "INSERT INTO Formule(id_piscine,duree_validite,nom,montant) VALUES(?,?,?,?)";
		if (con == null || con.isClosed()) {
            System.out.println("La connexion est fermée ou invalide.");
            
        }
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1,formule.getId_piscine());
		ps.setInt(2,formule.getDuree_validite());
		ps.setString(3,formule.getNom());
		ps.setInt(4,formule.getMontant());
		
		
		int result = ps.executeUpdate();
		
		databaseConnection.closePreparedStatement(ps);
		databaseConnection.closeConnection(con);
		
		if(result>0) {
			System.out.println("Insertion de "+ formule.getNom() +" confirmé");
		}else {
			System.out.println("Echec de l'insertion");
		}
		
		return result;
	}


	@Override
	public int update(Formule formule) throws SQLException {
		Connection con = databaseConnection.getInstance();
		String sql = "UPDATE Piscine SET id_formule=?,nom=?, id_piscine=?,duree_validite=? montant=?,nbr_personnes=? WHERE id=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, formule.getID());
		ps.setString(2, formule.getNom());
		ps.setInt(3, formule.getId_piscine());
		ps.setInt(4, formule.getDuree_validite());
		ps.setInt(5, formule.getMontant());
		
		
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
	public int delete(Formule formule) throws SQLException {
		Connection con = databaseConnection.getInstance();
		String sql = "DELETE FROM Formule WHERE id=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, formule.getID());
		
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
	
	public List<Formule> getFormulesByPiscineId(int piscineId) throws SQLException {
		Connection con = databaseConnection.getInstance();
		List<Formule> formuleList = new ArrayList<>();
        String sql = "SELECT * FROM formule WHERE id_piscine = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, piscineId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	Formule formule = new Formule(
            	 rs.getInt("id_formule"),  // Utilisez le nom correct de la colonne
                 rs.getInt("duree_validite"),
                 rs.getInt("id_piscine"),
                 rs.getString("nom"),
                 rs.getInt("montant")
                 
             );
                formuleList.add(formule);
            }
        }
        return formuleList;
    }

}
