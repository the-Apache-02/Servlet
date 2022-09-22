/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tech.aart.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 *
 * @author risha
 */
public class Helper {

    public static boolean deleteFile(String path) {
        boolean f = false;
        try {
            File file = new File(path);
            f = file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    public static boolean saveFile(InputStream is, String path) {
        boolean f = false;
        try {
            byte b[] = new byte[is.available()];
            is.read(b);
            FileOutputStream fout = new FileOutputStream(path);
            fout.write(b);
            fout.flush();
            fout.close();
            f = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
}
