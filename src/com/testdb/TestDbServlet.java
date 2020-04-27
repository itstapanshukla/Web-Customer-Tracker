package com.testdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private PreparedStatement ps = null;
    public TestDbServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//setup connection variables
		String user = "hbstudent";
		String pass = "hbstudent";
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&serverTimezone=UTC";
		String driver = "com.mysql.cj.jdbc.Driver";
		
		
		// get a connection to db
		try {
			PrintWriter out = response.getWriter();
			out.println("Connecting to db: " + jdbcUrl);
			Class.forName(driver);
			
			Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
			Statement st = myConn.createStatement();
			String query = "Select first_name from customers";
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{
				out.println(rs.getString(1));
			}
			out.println(".....................SUCCESSS.......................");

			myConn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
