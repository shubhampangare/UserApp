package com.facebook.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.facebook.dao.UserDAO;

@SuppressWarnings("serial")
@WebServlet("/addCategory")
public class AddCategoryServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
	        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "pangare");
	        UserDAO userDAO = new UserDAO(conn);
	        
	        String cat = req.getParameter("category");
	        int id = Integer.parseInt(req.getParameter("id"));
	        userDAO.addSmartFeedCategory(id, cat);
	        
	        req.getRequestDispatcher("smartfeed.jsp").include(req, resp);
		}
		catch (Exception e) {
			e.printStackTrace();		}
	}
}
