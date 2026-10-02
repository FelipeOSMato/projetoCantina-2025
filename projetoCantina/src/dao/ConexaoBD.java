package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {

	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/bdcantina", "root", "");
		}
		catch(SQLException e) {
			throw new RuntimeException();
		}
	}
	
}
