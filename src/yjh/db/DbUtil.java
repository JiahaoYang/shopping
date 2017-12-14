package yjh.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;





public class DbUtil {
	private static String DRIVER = "com.mysql.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost:3306/shopping1?" + 
								"useUnicode=true&characterEncoding=utf8&" + 
								"autoReconnect=true&useSSL=false";
	private static String USER = "root";
	private static String PASSWORD = "yjh961024";
	private static Connection conn = null;
	
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConn() { 
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
