package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class sqlconnect {
	
	private static Connection con;
	public static Connection getCon() {
		return con;
	}
	
	public static void setCon(Connection con) {
		sqlconnect.con = con;
	}

	 public sqlconnect(String url, String username, String password) {
	        try {
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            this.con = DriverManager.getConnection(url, username, password);
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

