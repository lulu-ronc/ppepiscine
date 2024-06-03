package dao.formule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import dao.databaseConnection;
import model.Formule;

public class FormuleDAOImpl implements FormuleDAO {

    @Override
    public Formule read(int id) throws SQLException {
        Connection con = databaseConnection.getInstance();
        Formule formule = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM Formule WHERE id_formule=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        rs = ps.executeQuery();

        if (rs.next()) {
            int oid = rs.getInt("id_formule");
            String nom = rs.getString("nom");
            int id_piscine = rs.getInt("id_piscine");
            int duree_validite = rs.getInt("duree_validite");
            int prix = rs.getInt("prix");
            formule = new Formule(oid, duree_validite, id_piscine, nom, prix);
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
        ResultSet rs = null;
        String sql = "SELECT * FROM Formule";

        Statement stmt = con.createStatement();
        rs = stmt.executeQuery(sql);

        while (rs.next()) {
            int oid = rs.getInt("id_formule");
            String nom = rs.getString("nom");
            int id_piscine = rs.getInt("id_piscine");
            int duree_validite = rs.getInt("duree_validite");
            int prix = rs.getInt("prix");
            formule = new Formule(oid, duree_validite, id_piscine, nom, prix);
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
        String sql = "INSERT INTO Formule(id_formule, id_piscine,duree_validite,nom,prix) VALUES(?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, formule.getID());
        ps.setInt(2, formule.getId_piscine());
        ps.setInt(3, formule.getDuree_validite());
        ps.setString(4, formule.getNom());
        ps.setInt(5, formule.getMontant());

        int result = ps.executeUpdate();

        databaseConnection.closePreparedStatement(ps);
        databaseConnection.closeConnection(con);

        if (result > 0) {
            System.out.println("Insertion de " + formule.getNom() + " confirmé");
        } else {
            System.out.println("Echec de l'insertion");
        }

        return result;
    }

    @Override
    public int update(Formule formule) throws SQLException {
        Connection con = databaseConnection.getInstance();
        String sql = "UPDATE Formule SET id_formule=?,id_piscine=?,nom=?,duree_validite=?,prix=? WHERE id_formule=?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, formule.getID());
        ps.setInt(2, formule.getId_piscine());
        ps.setString(3, formule.getNom());
        ps.setInt(4, formule.getDuree_validite());
        ps.setInt(5, formule.getMontant());

        int result = ps.executeUpdate();

        databaseConnection.closeStatement(ps);
        databaseConnection.closeConnection(con);

        if (result > 0) {
            System.out.println("Mise à jour confirmée");
        } else {
            System.out.println("Echec de la mise à jour");
        }

        return result;
    }

    @Override
    public int delete(Formule formule) throws SQLException {
        Connection con = databaseConnection.getInstance();
        String sql = "DELETE FROM Formule WHERE id_formule=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, formule.getID());

        int result = ps.executeUpdate();

        databaseConnection.closeStatement(ps);
        databaseConnection.closeConnection(con);

        if (result > 0) {
            System.out.println("Suppression confirmée");
        } else {
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
                        rs.getInt("id_formule"),
                        rs.getInt("duree_validite"),
                        rs.getInt("id_piscine"),
                        rs.getString("nom"),
                        rs.getInt("prix")
                );
                formuleList.add(formule);
            }
        }
        return formuleList;
    }

    public int getLastUsedId() throws SQLException {
        int lastUsedId = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            // Établir une connexion à la base de données
            connection = databaseConnection.Connect();

            // Écrire la requête SQL pour récupérer le dernier ID utilisé
            String sql = "SELECT MAX(id_formule) AS last_id FROM formule";
            
            // Préparer la déclaration
            statement = connection.prepareStatement(sql);

            // Exécuter la requête
            resultSet = statement.executeQuery();
            
            // Vérifier si le résultat contient des lignes
            if (resultSet.next()) {
                // Récupérer le dernier ID utilisé
                lastUsedId = resultSet.getInt("last_id");
            }
        } finally {
            // Fermer les ressources de la base de données
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        
        return lastUsedId;
    }

}
