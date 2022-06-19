package LoggyApp3.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

public static Connection getConnectionToDatabase() {
	Connection connection = null;

	try {

		// load the driver class
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("MySQL JDBC Driver Registered");

		// get hold of the DriverManager
		connection = DriverManager.getConnection
				("jdbc:mysql://localhost:3306/loggyapp", "root", "8552369279abc$");
	} catch (ClassNotFoundException e) {
		System.out.println("Connection not found");
		e.printStackTrace();

	}

	catch (SQLException e) {
		System.out.println("Connection Failed");
		e.printStackTrace();

	}

	if (connection != null) {
		System.out.println("Successfully connected to database");
	}
	return connection;
   }

}
