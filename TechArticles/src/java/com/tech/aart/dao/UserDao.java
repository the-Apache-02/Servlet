package com.tech.aart.dao;

/**
 *
 * @author risha
 */
import com.tech.aart.entities.Post;
import com.tech.aart.entities.User;
import com.tech.aart.helper.ConnectionProvider;
import java.sql.*;

public class UserDao {

    private Connection con;

    public UserDao(Connection con) {
        this.con = con;
    }

    public UserDao() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean saveUser(User user) {
        boolean f = false;
        try {

            String query = "insert into user(name,email,password,gender,about) values(?,?,?,?,?)";
            PreparedStatement pstmt = this.con.prepareStatement(query);

            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getGender());
            pstmt.setString(5, user.getAbout());
            pstmt.executeUpdate();
            f = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    public User getEmailPassword(String email, String password) {
        User user = null;
        try {
            String query = "select *from user where email=? and password=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setId(rs.getInt("id"));
                user.setGender(rs.getString("gender"));
                user.setAbout(rs.getString("about"));
                user.setDateTime(rs.getTimestamp("rtime"));
                user.setProfile(rs.getString("profile"));
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean updateUser(User user) {
        boolean f = false;
        try {
            String query = "update user set name=?,email=?,password=?,about=?,profile=? where id=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getAbout());
            pstmt.setString(5, user.getProfile());
            pstmt.setInt(6, user.getId());
            pstmt.executeUpdate();
            f = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
//get username for show_blog through userId

    public User getuserNameForShowBlog(int id) {

        User user = null;
        try {
            String query = "select *from user where id=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setId(rs.getInt("id"));
                user.setGender(rs.getString("gender"));
                user.setAbout(rs.getString("about"));
                user.setDateTime(rs.getTimestamp("rtime"));
                user.setProfile(rs.getString("profile"));
            }
        } catch (Exception e) {
        }
        return user;
    }
}
