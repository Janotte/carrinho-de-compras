package br.inf.hobby.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	
	private static Connection connection = null;
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if (connection == null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/carrinho_db","ordemUser","dW9QO5k&yBAEcXG56648");
			System.out.println("Conectado!");
		}
		 return connection;
	}
}
