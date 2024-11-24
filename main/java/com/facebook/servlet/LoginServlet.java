package com.facebook.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.facebook.dao.UserDAO;
import com.facebook.model.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	HttpSession session = request.getSession();
    	//User user=(User)session.getAttribute("user");
//    	if(user==null)
//    	{
//    		response.sendRedirect("error");
//    	}
    	
    	String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "pangare")) {
                UserDAO userDAO = new UserDAO(conn);
                
                 User user= userDAO.getUserByEmail(email);

//                if (user != null && user.getPassword().equals(password)) 
//                {
//                   // HttpSession session = request.getSession();
//                    session.setAttribute("user", user);
//                    response.sendRedirect("smartfeed.jsp");
//                } else {
//                    request.setAttribute("error", "Invalid email or password");
//                    request.getRequestDispatcher("login.jsp").forward(request, response);
//                }
                 if(user!=null)
                 {
                	 session.setAttribute("user", user);
                     response.sendRedirect("smartfeed.jsp"); 
                 }
                 else
                 {
                	 request.setAttribute("error", "Invalid email or password");
                     request.getRequestDispatcher("login.jsp").forward(request, response); 
                 }
                 
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("Database connection problem", e);
        }
    }
}
