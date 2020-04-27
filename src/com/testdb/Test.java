package com.testdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test {

	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&serverTimezone=UTC";
		String user = "hbstudent";
		String pass = "hbstudent";
		try {
		System.out.println("Connecting to database: " + url);
		
		Connection myConn = 
				DriverManager.getConnection(url, user, pass);
		
		System.out.println("Connection successful" + myConn);
		Statement st = myConn.createStatement();
		String query = "Select * from customers";
		ResultSet rs = st.executeQuery(query);
		System.out.println(".....................SUCCESSS.......................");
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
