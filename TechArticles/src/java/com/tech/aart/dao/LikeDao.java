package com.tech.aart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LikeDao {

    Connection con;

    public LikeDao(Connection con) {
        this.con = con;
    }
//insert the like in the database

    public boolean insertLike(int pid, int uid) {
        boolean f = false;
        try {
            String q = "insert into liked(pid,uid) value(?,?)";
            PreparedStatement pstmt = this.con.prepareStatement(q);
            pstmt.setInt(1, pid);
            pstmt.setInt(2, uid);
            pstmt.executeUpdate();
            f = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
//count a like on the post

    public int countLike(int pid) {
        int count = 0;
        try {
            String query = "select count(*) from liked where pid=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, pid);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
// Is user like this particular post or not

    public boolean isLiked(int pid, int uid) {
        boolean f = false;
        try {
            String query = "select * from liked where pid=? and uid=?";
            PreparedStatement pstmt = this.con.prepareStatement(query);
            pstmt.setInt(1, pid);
            pstmt.setInt(2, uid);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                f = true;
            }
        } catch (Exception e) {
        }
        return f;
    }

//disliked a post
    public boolean disLiked(int pid, int uid) {
        boolean f = false;
        try {
            String query = "Delete from liked where pid=? and uid=?";
            PreparedStatement pstmt = this.con.prepareStatement(query);
            pstmt.setInt(1, pid);
            pstmt.setInt(2, uid);
            pstmt.executeUpdate();
            f = true;
        } catch (Exception e) {
        }
        return f;
    }
}
