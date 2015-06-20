/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.helloservlet;

import com.mycompany.helloservlet.model.Post;
import com.mycompany.helloservlet.model.SuccessMessage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ricardo
 */
@WebServlet(name = "Posts", urlPatterns = {"/Posts"})
public class Posts extends HttpServlet {

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
        
        HttpSession session = request.getSession(true);
        if (session.getAttribute("username") == null)
        {
            SuccessMessage message = new SuccessMessage(false, "Please sign in first");
            session.setAttribute("message", message);
            response.sendRedirect("index.jsp");
        }
        
        String username = (String) session.getAttribute("username");
        
        request.setAttribute("posts", new Post().getPosts(username));
        request.getRequestDispatcher("home.jsp").forward(request, response);
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
        
        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute("username");
        
        Post post = new Post();
        post.setMessage(request.getParameter("message"));
        post.setUsername(username);
        post.setDate(new Date());
        post.createPost();
        
        response.sendRedirect("Posts");
    }
}
