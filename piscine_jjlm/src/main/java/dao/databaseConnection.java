package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.http.impl.DefaultBHttpClientConnection;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class databaseConnection {
	
	private static Connection connexionDB;
	private static final String USERNAME= "Jimmy";
	private static final String PASSWORD= "admin";
	private static final String SQL_SERVER = "PCDEJIM\\SQLEXPRESS01"; 
	private static final String DATABASE= "UnePiscine";

	public Connection Connect() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("pilotes chargés");
		}catch(ClassNotFoundException e) {
			System.out.println("Pilotes non chargés" + e);
		}


		try {
			SQLServerDataSource ds = new SQLServerDataSource();
			ds.setUser(USERNAME);
			ds.setPassword(PASSWORD);
			ds.setServerName(SQL_SERVER);
			ds.setDatabaseName(DATABASE);
			connexionDB = ds.getConnection();
			//connexionDB = DriverManager.getConnection(SQL_SERVER, USERNAME, PASSWORD);
			System.out.println("connecté");
		}catch(SQLException e){
			System.out.println("Connexion échouée" + e);
		}
		return connexionDB;
	}

	public static Connection getInstance(){
		if(connexionDB== null) {
			new databaseConnection();
			System.out.println("connecté");
		}
		return connexionDB;	
	}

}
