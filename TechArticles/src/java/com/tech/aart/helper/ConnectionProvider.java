/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tech.aart.helper;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author risha
 */
public class ConnectionProvider {

    private static Connection con;

    public static Connection getConnection() {
        try {
            if (con == null) {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/techaart", "root", "Selin");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
