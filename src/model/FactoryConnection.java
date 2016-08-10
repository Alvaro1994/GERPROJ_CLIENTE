package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class FactoryConnection {
	private static final String URL_MYSQL ="jdbc:mysql://localhost/restaurante?useSSL=false";
	private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";

	public static Connection getConnection() {
		try {
			Class.forName(DRIVER_CLASS);
			return DriverManager.getConnection(URL_MYSQL,USERNAME,PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
