package com.facebook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.facebook.model.Post;
import com.facebook.model.User;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public int createPost(Post p)
    {	
        try {
        	System.out.println("Create Post");
			PreparedStatement ps = connection.prepareStatement("insert into posts values(?,?,?,?,?,?)");
			ps.setInt(1, p.getPostId());
			ps.setInt(2, p.getAccountId());
			//Clob clb= connection.createClob();
			ps.setString(3, p.getPostText());
			ps.setString(4, p.getMediaAttachment());
			ps.setString(5,p.getPrivacySetting());
			ps.setTimestamp(6, (Timestamp) p.getSubmittedDate());
			
			ps.executeUpdate();
			System.out.println("Posted");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return 0;    	
    }
    public User getUserById(int userId) throws SQLException {
        String query = "SELECT * FROM Users WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("user_id"),
                        rs.getString("user_first_name"),
                        rs.getString("user_last_name"),
                        rs.getString("email"),
                        rs.getString("password")
                    );
                }
                
                
            }
        }
        return null;
    }

    public User getUserByEmail(String email) throws SQLException {
        String query = "SELECT * FROM Users WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("user_id"),
                        rs.getString("user_first_name"),
                        rs.getString("user_last_name"),
                        rs.getString("email"),
                        rs.getString("password")
                    );
                }
            }
        }
        return null;
    }

    public void addUser(User user) throws SQLException {
        String query = "INSERT INTO Users (user_id, user_first_name, user_last_name, email, password) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, user.getUserId());
            stmt.setString(2, user.getFirstName());
            stmt.setString(3, user.getLastName());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPassword());
            stmt.executeUpdate();
        }
    }

    public List<String> getSmartFeedCategories(int userId) throws SQLException {
        List<String> categories = new ArrayList<>();
        String query = "SELECT category FROM SmartFeedPreferences WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    categories.add(rs.getString("category"));
                }
            }
        }
        return categories;
    }

    public void addSmartFeedCategory(int userId, String category) throws SQLException {
        String query = "INSERT INTO SmartFeedPreferences (user_id, category) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setString(2, category);
            stmt.executeUpdate();
        }
    }
    
    public int getNextUserId() throws SQLException {
        String query = "SELECT MAX(user_id) FROM Users";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt(1) + 1;
            }
        }
        return 1;
    }
    
    public int getNextAccountId() throws SQLException {
        String query = "SELECT MAX(USER_ID) FROM posts";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt(1) + 1;
            }
        }
        return 1;
    }
    
    public List<Post> getPosts()
    {
    	List<Post> posts=  null ;
    	try 
    	{
			  String getPosts="select * from posts";
			  PreparedStatement stm = this.connection.prepareStatement(getPosts);
			  ResultSet rs = stm.executeQuery();
			  posts=new ArrayList<Post>();
			  while(rs.next())
			  {
				  Post post = new Post(); 
				  post.setPostId(rs.getInt(1));
				  post.setMediaAttachment(rs.getString(4));
				  post.setAccountId(rs.getInt(2));
				  post.setPostText(rs.getString(3));
				  post.setPrivacySetting(rs.getString(5));
				  post.setSubmittedDate(rs.getDate(6));
				  
				  posts.add(post);
			  }
			  
		} 
    	catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return posts;
    }

}