package tests;


import static org.junit.Assert.assertSame;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

import dao.databaseConnection;

public class databaseConnectionTest {

	
	@Test
    public void testSingletonInstance() {
        Connection instanceTest1 = databaseConnection.getInstance();
        assertNotNull("L'instance de test 1 du singleton est null", instanceTest1);
        Connection instanceTest2 = databaseConnection.getInstance();
        assertSame("les instances ne sont pas les mêmes ",instanceTest1, instanceTest2);
    }

	private void assertNotNull(String string, Connection instance) {
		// TODO Auto-generated method stub
		
	}
	
	 @Test
	    public void testDatabaseConnection() {
	        Connection connection = databaseConnection.Connect();
	        assertNotNull("Pas de connexion",connection);
	    }

	    
}
