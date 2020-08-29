/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Documents;

import Database.DatabaseConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nelli
 */
@WebServlet(name = "Storage", urlPatterns = {"/documents"})
public class Storage extends HttpServlet {

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
            out.println("<title>Servlet Storage</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Storage at " + request.getContextPath() + "</h1>");
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
      
        try{
         Class.forName("com.mysql.jdbc.Driver");
         Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3307/writing", "root", "");
         PreparedStatement file=connection.prepareStatement("SELECT title, content FROM Documents WHERE title=? AND email_address=?");
         file.setString(1, request.getParameter("title"));
         file.setString(2, request.getParameter("email").toUpperCase());
        
         ResultSet results=file.executeQuery();
         response.setContentType("text/plain");
         if(results.next()){
             /*Period seperates title of document and content of document.
             Title is included to determine if file exists, and content
             is included, so it can be passed to the client side
             */
           response.getWriter().write(results.getString("title")+"."+results.getString("content"));
           
         }
         else{
             response.getWriter().write("fail");
            // request.getSession().setAttribute("content", "");
         }
         connection.close();
                     
        }
        catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
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
                try{
         Class.forName("com.mysql.jdbc.Driver");
           Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3307/writing", "root", "");
         PreparedStatement save=connection.prepareStatement("INSERT INTO Documents VALUES (?, ?, ?);");
         
         save.setString(1, request.getParameter("email").toUpperCase());
         save.setString(2, request.getParameter("title"));
         save.setString(3, request.getParameter("content"));
         save.executeUpdate();
         
         connection.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
            //If this occurs, then the reason is probably that the user already has a document with the name he/she entered
            response.getWriter().print("error");
        }
        catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Saves text to database, and retrieves it from the database";
    }// </editor-fold>

}
