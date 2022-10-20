
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.sql.ResultSetMetaData;

public class Connector {
	
	public final String USERNAME = "root";
	public final String PASSWORD = "";
	public final String DATABASE = "stim";
	public final String HOST = "localhost:3306";
	
	public Connection con;
	public Statement state;
	public ResultSet resultS;
	public ResultSetMetaData rSM;
	
	public Connector() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DATABASE, USERNAME, PASSWORD);
			state = con.createStatement();
		}catch (Exception e) {
			System.out.println("Failed to connect the database, the system is terminated!");
			System.exit(0);
		}
	}
	
	public ResultSet executeQuery(String query) {
		ResultSet resultS = null;
		try {
			resultS = state.executeQuery(query);
			rSM = resultS.getMetaData();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resultS;
	}
	
	public void executeUpdate(String query) {
		try {
			state.executeUpdate(query);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public PreparedStatement prepareStatement(String query) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ps;
	}

}
