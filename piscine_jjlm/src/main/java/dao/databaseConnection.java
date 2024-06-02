package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class databaseConnection {

	private static Connection connexionDB;
	private static final String USERNAME= "Jimmy";
	private static final String PASSWORD= "0813Ob5kOb5k";
	private static final String SQL_SERVER = "PCDEJIM\\SQLEXPRESS01"; 
	private static final String DATABASE= "UnePiscine";

	public static Connection Connect() {

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
			//System.out.println("connecté");
		}catch(SQLException e){
			System.out.println("Connexion échouée" + e);
		}
		return connexionDB;
	}


	public static Connection getInstance(){
		if(connexionDB== null) {
			databaseConnection con = new databaseConnection();
			connexionDB = con.Connect();
			System.out.println("connecté");

		}
		return connexionDB;	
	}


	public static void closeConnection(Connection connection) throws SQLException{
		connection.close();
	}

	public static void closeConnectionNoArg() {
        if (connexionDB != null) {
            try {
                connexionDB.close();
                connexionDB = null; // Reset the instance to ensure it can be reinitialized
                System.out.println("Connexion fermée.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
	
	public static void closeConnectionNoArg() {
        if (connexionDB != null) {
            try {
                connexionDB.close();
                connexionDB = null; // Reset the instance to ensure it can be reinitialized
                System.out.println("Connexion fermée.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

	public static void closeStatement(Statement statement) throws SQLException{
		statement.close();
	}

	public static void closePreparedStatement(PreparedStatement preparedStatement) throws SQLException{
		preparedStatement.close();
	}

	public static void closeResultSet(ResultSet resultset) throws SQLException{
		resultset.close();
	}

}
