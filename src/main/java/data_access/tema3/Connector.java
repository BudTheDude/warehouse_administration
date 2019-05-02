package data_access.tema3;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Connector {

	Connection connection = null;
	public Connector() {
		
		try {
			connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/mydb?user=bud&password=a31b30c29z1");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public Connection retrieveConnection() {
		return connection;
	}
	

}
