package br.com.activity.hibenate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static String URL_INSTANCE_SQL = "jdbc:mysql://localhost:3306/activityplanner"; 
	private static String NAME_INSTANCE_SQL = "root";
	private static String PASSWORD_INSTANCE_SQL = "4056";

	//	private String URL_INSTANCE_SQL = "jdbc:mysql://localhost:3306/activityplanner"; 
	//	private String NAME_INSTANCE_SQL = "root";
	//	private String PASSWORD_INSTANCE_SQL = "4056";

	public Connection getConnection() throws ClassNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			return DriverManager.getConnection(
					URL_INSTANCE_SQL,
					NAME_INSTANCE_SQL ,
					PASSWORD_INSTANCE_SQL);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
