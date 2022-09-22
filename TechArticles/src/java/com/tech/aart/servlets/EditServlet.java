/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.tech.aart.servlets;

import com.tech.aart.dao.UserDao;
import com.tech.aart.entities.Message;
import com.tech.aart.entities.User;
import com.tech.aart.helper.ConnectionProvider;
import com.tech.aart.helper.Helper;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author risha
 */
@MultipartConfig
public class EditServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditServlet</title>");
            out.println("</head>");
            out.println("<body>");

//fetch all the data which is came from profile.jsp to edit the user information
            String editEmail = request.getParameter("editEmail");
            String editName = request.getParameter("editName");
            String editPassword = request.getParameter("editPassword");
            String editAbout = request.getParameter("editAbout");
            Part part = request.getPart("editImg");
            String editImage = part.getSubmittedFileName();
//Create the session
            HttpSession s = request.getSession();
            User user = (User) s.getAttribute("currentUser");//
            user.setName(editName);
            user.setEmail(editEmail);
            String previousPassword = user.getPassword();

            if(!editPassword.equals("null")) {
                user.setPassword(editPassword);
            } else {
                user.setPassword(previousPassword);
            }

            user.setAbout(editAbout);
            String oldfile = user.getProfile();
            user.setProfile(editImage);
            UserDao userDao = new UserDao(ConnectionProvider.getConnection());
//            out.println("above boolean");
            boolean ans = userDao.updateUser(user);
            if (ans == true) {

                String path = request.getRealPath("/") + "pics" + File.separator + user.getProfile();
                String oldpath = request.getRealPath("/") + "pics" + File.separator + oldfile;
//delete the old file
                if (!oldpath.equals("default.png")) {
                    Helper.deleteFile(oldpath);
                }

                if (Helper.saveFile(part.getInputStream(), path)) {
                    Message msg = new Message("Profile Updated Successfully", "success", "alert-success");
                    s.setAttribute("message", msg);
                } else {
                    Message msg = new Message("Invalid Details Try again", "error", "alert-danger");
                    s.setAttribute("message", msg);
                }
            } else {
                Message msg = new Message("Invalid Details Try again", "error", "alert-danger");
                s.setAttribute("message", msg);
            }
            response.sendRedirect("profile.jsp");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
