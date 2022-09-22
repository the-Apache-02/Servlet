/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tech.aart.entities;

import java.sql.Timestamp;

/**
 *
 * @author risha
 */
public class Post {

    private int pId;
    private String pTitle;
    private String pContent;
    private String pCode;
    private String pPic;
    private int catId;
    private Timestamp pDate;
    private int userId;

    public Post(int pId, String pTitle, String pContent, String pCode, String pPic, int catId, Timestamp pDate, int userId) {
        this.pId = pId;
        this.pTitle = pTitle;
        this.pContent = pContent;
        this.pCode = pCode;
        this.pPic = pPic;
        this.catId = catId;
        this.pDate = pDate;
        this.userId = userId;
    }

    public Post() {
    }

    public Post(String pTitle, String pContent, String pCode, String pPic, int catId, Timestamp pDate, int userId) {
        this.pTitle = pTitle;
        this.pContent = pContent;
        this.pCode = pCode;
        this.pPic = pPic;
        this.catId = catId;
        this.pDate = pDate;
        this.userId = userId;
    }

    public Post(int pId, String pTitle, String pContent, String pCode, String pPic, Timestamp pDate, int catId, String userId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getpTitle() {
        return pTitle;
    }

    public void setpTitle(String pTitle) {
        this.pTitle = pTitle;
    }

    public String getpContent() {
        return pContent;
    }

    public void setpContent(String pContent) {
        this.pContent = pContent;
    }

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public String getpPic() {
        return pPic;
    }

    public void setpPic(String pPic) {
        this.pPic = pPic;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public Timestamp getpDate() {
        return pDate;
    }

    public void setpDate(Timestamp pDate) {
        this.pDate = pDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
