/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tech.aart.dao;

import com.tech.aart.entities.Category;
import com.tech.aart.entities.Post;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author risha
 */
public class PostDao {

    Connection con;

    public PostDao(Connection con) {
        this.con = con;
    }

    public ArrayList<Category> getAllCategories() {
        ArrayList<Category> list = new ArrayList<Category>();
        try {
            String query = "Select *from categories";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int cid = rs.getInt("cid");
                String name = rs.getString("name");
                String description = rs.getString("description");
                Category c = new Category(cid, name, description);
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean savePost(Post p) {
        boolean f = false;
        try {
            String query = "insert into post(pTitle,pContent,pCode,pPic,catId,userId) value(?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, p.getpTitle());
            stmt.setString(2, p.getpContent());
            stmt.setString(3, p.getpCode());
            stmt.setString(4, p.getpPic());
            stmt.setInt(5, p.getCatId());
            stmt.setInt(6, p.getUserId());
            stmt.executeUpdate();
            f = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

//get all posts
    public List<Post> getAllPost() {
        List<Post> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = con.prepareStatement("select *from post order by pId desc");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int pId = rs.getInt("pId");
                String pTitle = rs.getString("pTitle");
                String pContent = rs.getString("pContent");
                String pCode = rs.getString("pCode");
                String pPic = rs.getString("pPic");
                int catId = rs.getInt("catId");
                Timestamp pDate = rs.getTimestamp("pDate");
                int userId = rs.getInt("userId");
                Post post = new Post(pId, pTitle, pContent, pCode, pPic, catId, pDate, userId);
                list.add(post);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

//get post by Id
    public List<Post> getPostById(int catId) {

        List<Post> list2 = new ArrayList<>();
        try {

            PreparedStatement pstmt = con.prepareStatement("select *from post where catId=?");
            pstmt.setInt(1, catId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                String pTitle = rs.getString("pTitle");
                String pContent = rs.getString("pContent");
                String pCode = rs.getString("pCode");
                String pPic = rs.getString("pPic");

                Timestamp pDate = rs.getTimestamp("pDate");
                int userId = rs.getInt("userId");
                Post post = new Post(pTitle, pContent, pCode, pPic, catId, pDate, userId);
                list2.add(post);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list2;
    }

//get blog content when click on read more option
    public Post getBlogContent(int postId) {
        Post p = null;
        try {
            String q = "select * from post where pId=?";
            PreparedStatement pstmt = this.con.prepareStatement(q);
            pstmt.setInt(1, postId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int pId = rs.getInt("pId");
                String pTitle = rs.getString("pTitle");
                String pContent = rs.getString("pContent");
                String pCode = rs.getString("pCode");
                String pPic = rs.getString("pPic");
                int catId = rs.getInt("catId");
                Timestamp pDate = rs.getTimestamp("pDate");
                int userId = rs.getInt("userId");
                p = new Post(pId, pTitle, pContent, pCode, pPic, catId, pDate, userId);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }
}
