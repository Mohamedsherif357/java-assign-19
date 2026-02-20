package com.employee.com;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class UpdateEmployeeServlet
 */
//@WebServlet("/UpdateEmployeeServlet")
public class UpdateEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateEmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  response.setContentType("text/html");
	        PrintWriter out = response.getWriter();

	        int id = Integer.parseInt(request.getParameter("id"));
	        double salary = Double.parseDouble(request.getParameter("salary"));

	        try {
	            // Load Driver
	            Class.forName("com.mysql.cj.jdbc.Driver");

	            // Create Connection
	            Connection con = DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/companydb",
	                    "root",
	                    "sherif@786");

	            // Prepare SQL
	            String sql = "UPDATE employee SET salary=? WHERE id=?";

	            PreparedStatement ps = con.prepareStatement(sql);
	            ps.setDouble(1, salary);
	            ps.setInt(2, id);

	            int rows = ps.executeUpdate();

	            if (rows > 0) {
	                out.println("<h3>Salary Updated Successfully!</h3>");
	            } else {
	                out.println("<h3>Employee ID Not Found!</h3>");
	            }

	            con.close();

	        } catch (Exception e) {
	            e.printStackTrace();
	            out.println("<h3>Error: " + e.getMessage() + "</h3>");
	        }
	    }
	}