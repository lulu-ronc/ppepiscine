package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class databaseConnection {
	private Connection connexionDB;

	private static final String username= "jimmy";
	private static final String password= "admin";
	private static final String SQL_SERVER = "PCDEJIM\\SQLEXPRESS01"; 

	public Connection Connect() {
		try {

			connexionDB = DriverManager.getConnection(SQL_SERVER, username, password);
			System.out.println("connecté");
		}catch(Exception e){
			System.out.println("Connexion échouée" + e);
		}
		return connexionDB;
	}
}
