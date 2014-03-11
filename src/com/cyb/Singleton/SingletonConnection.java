package com.cyb.Singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SingletonConnection {

	private static Connection connection = null;
	private SingletonConnection() {

	}

	public synchronized static Connection getConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
		}

		System.out.println("MySQL JDBC Driver Registered!");

		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test?autoReconnect=true&relaxAutoCommit=true", "root", "root");
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
	}
		return connection;
	}
}
