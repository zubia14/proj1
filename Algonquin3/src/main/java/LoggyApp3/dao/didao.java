package LoggyApp3.dao;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class didao {
	
	private static final String USERNAME = "root"; 
	private static final String PASSWORD = "8552369279abc$";
	private static final String CONN_STRING = "jdbc:mysql://localhost:3306/loggyuserinfo";
	
	public static void main(String[] args) throws SQLException {
		//Class. forName("com.mysql.jdbc.Driver");
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			System.out.println("Connected!"); 
			
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("SELECT * FROM logs");
			rs.last();
			System.out.println("Number of rows: " + rs.getRow());
			} 
		
		catch (SQLException e) {
			System.err.println(e); 
			} 
		
		finally { 
			if (conn != null) {
			conn.close();
          }
      }
   }
}