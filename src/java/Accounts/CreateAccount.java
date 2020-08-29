/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accounts;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Database.DatabaseConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Database.DatabaseConnection;
/**
 *
 * @author nelli
 */
@WebServlet(name = "CreateAccount", urlPatterns = {"/createAccount"})
public class CreateAccount extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  

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
             ActivationCode code=new ActivationCode();
             code.SendEmailActivationCode(request.getParameter("email"), response);
             Class.forName("com.mysql.jdbc.Driver");
           Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3307/writing", "root", "");
         PreparedStatement creation=connection.prepareStatement("INSERT INTO Accounts VALUES(?, ?, ?, ?, ?, ?)");
         System.out.println(request.getParameter("firstName"));
         creation.setString(1, request.getParameter("firstName"));
         creation.setString(2, request.getParameter("lastName"));
         creation.setString(3, request.getParameter("email"));
         creation.setString(4, request.getParameter("password"));
         creation.setString(5, "NO");
         creation.setString(6, code.GetActivationCode());
         creation.executeUpdate();
         connection.close();
        }
        catch(Exception ex ){
           
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
             ActivationCode code=new ActivationCode();
             code.SendEmailActivationCode(request.getParameter("email"), response);
             Class.forName("com.mysql.jdbc.Driver");
           Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3307/writing", "root", "");
         PreparedStatement creation=connection.prepareStatement("INSERT INTO Accounts VALUES(?, ?, ?, ?, ?, ?)");
         System.out.println(request.getParameter("firstName"));
         creation.setString(1, request.getParameter("firstName"));
         creation.setString(2, request.getParameter("lastName"));
         creation.setString(3, request.getParameter("email").toUpperCase());
         creation.setString(4, request.getParameter("password"));
         creation.setString(5, "NO");
         creation.setString(6, code.GetActivationCode());
         creation.executeUpdate();
         connection.close();
        }
        catch(Exception ex ){
           
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
        return "Creates a new account for the user";
    }// </editor-fold>

}
