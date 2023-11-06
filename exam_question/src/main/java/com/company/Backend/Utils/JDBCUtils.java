package com.company.Backend.Utils;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {

	private Connection connection;

	private void initConnection() {
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream("src/main/resources/database.properties"));

			// register the driver class with DriverManager
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Step 1: get a Database Connection
			connection = DriverManager.getConnection(
					properties.getProperty("url"), 
					properties.getProperty("username"),
					properties.getProperty("password"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		try {
			if (connection == null || connection.isClosed()) {
				initConnection();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}

	public void disconnect() {
		try {
			if (connection != null && connection.isClosed()) {
				connection.close();
				connection = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
