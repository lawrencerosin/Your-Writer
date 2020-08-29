/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accounts;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nelli
 */
@WebServlet(name = "CodeManagement", urlPatterns = {"/activationCode"})
public class CodeManagement extends HttpServlet {

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
            out.println("<title>Servlet CodeManagement</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CodeManagement at " + request.getContextPath() + "</h1>");
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
          PreparedStatement activationCodeQuery=connection.prepareStatement("SELECT activation_code FROM Accounts WHERE email_address=?");
          activationCodeQuery.setString(1, request.getParameter("email").toUpperCase());
          ResultSet results=activationCodeQuery.executeQuery();
          //If the email is in the database
          if(results.next()){
             ActivationCode activationCode=new ActivationCode();
             activationCode.SetActivationCode(results.getString("activation_code"));
             activationCode.SendEmailActivationCode(request.getParameter("email"), response);
             response.getWriter().print("success");
          }
          else
              response.getWriter().print("fail");
          
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        catch(ClassNotFoundException ex){
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
           PreparedStatement activateAccount=connection.prepareStatement("UPDATE Accounts SET activated='YES' WHERE email_address=? AND activation_code=?", Statement.RETURN_GENERATED_KEYS);
           activateAccount.setString(1, request.getParameter("email").toUpperCase());
           activateAccount.setString(2, request.getParameter("code"));
           int rowsUpdated=activateAccount.executeUpdate();
           if(rowsUpdated>0)
               response.getWriter().print("success");
           else
               response.getWriter().print("fail");
       }
       catch(SQLException ex){
           ex.printStackTrace();
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
        return "Resends the activation code, and activates the user's account.";
    }// </editor-fold>

}
