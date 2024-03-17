package dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.databaseConnection;
import model.Admin;


public class AdminDAOImpl implements AdminDAO {

	@Override
	public Admin get(int id) throws SQLException {
		Connection con = databaseConnection.getInstance();
		Admin admin = null;
		ResultSet rs =null;
		
		String sql ="SELECT * FROM Admin WHERE id_admin=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1,id);
		rs=ps.executeQuery();
		
		if(rs.next()) {
			int oid = rs.getInt("id");
			String mdp = rs.getString("mdp");
			int rang = rs.getInt("rang");
			admin =new Admin(oid, mdp, rang);
		}
		
		
		databaseConnection.closeResultSet(rs);
		databaseConnection.closePreparedStatement(ps);
		databaseConnection.closeConnection(con);
		return admin;
	}

	@Override
	public List<Admin> getAll() throws SQLException {
		Connection con = databaseConnection.getInstance();
		List<Admin> listAdmin = new ArrayList<>();
		Admin admin = null;
		ResultSet rs =null;
		String sql ="SELECT * FROM Admin";
		
		Statement stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			int oid = rs.getInt("id");
			String mdp = rs.getString("mdp");
			int rang = rs.getInt("rang");
			admin =new Admin(oid, mdp, rang);
			listAdmin.add(admin);
		}
		
		databaseConnection.closeResultSet(rs);
		databaseConnection.closeStatement(stmt);
		databaseConnection.closeConnection(con);	
		return listAdmin;
	}

	@Override
	public int add(Admin admin) throws SQLException {
		Connection con = databaseConnection.getInstance();
		String sql = "INSERT INTO Admin(id,nom,detente,) VALUES(?,?,?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1,admin.getId_admin());
		ps.setString(2,admin.getMdp());
		ps.setInt(3,admin.getRang());
		
		int result = ps.executeUpdate();
		
		databaseConnection.closePreparedStatement(ps);
		databaseConnection.closeConnection(con);
		
		if(result>0) {
			System.out.println("Insertion de "+ admin.getId_admin() +" confirmé");
		}else {
			System.out.println("Echec de l'insertion");
		}		return result;
	}

	@Override
	public int update(Admin admin) throws SQLException {
		Connection con = databaseConnection.getInstance();
		String sql = "UPDATE Admin SET id_admin=?,nom=?, detente=? WHERE id_admin=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1,admin.getId_admin());
		ps.setString(2,admin.getMdp());
		ps.setInt(3,admin.getRang());
		
		int result = ps.executeUpdate();
		
		databaseConnection.closeStatement(ps);
		databaseConnection.closeConnection(con);
		
		if(result>0) {
			System.out.println("Mise à jour confirmée");
		}else {
			System.out.println("Echec de la mise à jour");
		}		return result;
	}

	@Override
	public int delete(Admin admin) throws SQLException {
		Connection con = databaseConnection.getInstance();
		String sql = "DELETE FROM Admin WHERE id_admin=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, admin.getId_admin());
		
		int result = ps.executeUpdate();
		
		databaseConnection.closeStatement(ps);
		databaseConnection.closeConnection(con);
		
		if(result>0) {
			System.out.println("Suppression confirmée");
		}else {
			System.out.println("Echec de la suppression");
		}		return result;
	}

}
