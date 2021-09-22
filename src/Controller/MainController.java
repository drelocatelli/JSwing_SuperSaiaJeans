package Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Database.*;

public class MainController {

	public static String title, version, login, password;
	
	public MainController() {
		super();
		getConfig();
	}
	
	public void getConfig() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			
			conn = Connect.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM config");
			
			while(rs.next()) {
				title = rs.getString("title");
				version = rs.getString("version");
				login = rs.getString("login");
				password = rs.getString("senha");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			Connect.closeResultSet(rs);
			Connect.closeStatement(st);
			Connect.closeConnection();
		}
		
	}

}
