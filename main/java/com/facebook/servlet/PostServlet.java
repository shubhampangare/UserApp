package com.facebook.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.facebook.dao.UserDAO;
import com.facebook.model.Post;
import com.facebook.model.User;

@WebServlet("/PostServlet")
@SuppressWarnings("serial")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)
public class PostServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("PostServlet.doPost()");
		
		
		HttpSession session = req.getSession(false);
		
    	User user=(User)session.getAttribute("user");
    	System.out.println(user);
    	System.out.println(user);
    	if(user==null)
    	{
    		resp.sendRedirect("error");
    	}
    	
    	
    	
    	 final String SAVE_DIR="images";
    	 String savePath = "C:" + File.separator + SAVE_DIR;
    	 
    	 File fileSaveDir=new File(savePath);
    	 
//         if(!fileSaveDir.exists()){
//             fileSaveDir.mkdir();
//         }
         
         System.out.println(savePath);
         
         Part part=req.getPart("media");
         String fileName=part.getSubmittedFileName();
         part.write(savePath + File.separator + fileName);
    	
    	System.out.println("success");
    	
    	
    	
    	
    	
    	
		String postText = req.getParameter("postText");
		//String media = req.getParameter("media");
		String privacy = req.getParameter("privacy");
		
		System.out.println(postText+" "+" "+privacy);
		
		
		
		try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "pangare")) {
                int userId = user.getUserId();
                UserDAO userDAO = new UserDAO(conn);
                
                int nextAccountId = userDAO.getNextAccountId();
                
                Date date = new Date();
                
                Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
                Post p = new Post(userId, nextAccountId, postText,fileName , privacy, timeStamp);
                System.out.println("PostServlet.doPost()::1");
                userDAO.createPost(p);
                System.out.println("PostServlet.doPost()::2");
                req.getRequestDispatcher("smartfeed.jsp").include(req, resp);
               
            }
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
