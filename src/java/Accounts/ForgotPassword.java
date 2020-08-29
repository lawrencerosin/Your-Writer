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
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nelli
 */
@WebServlet(name = "ForgotPassword", urlPatterns = {"/forgotPassword"})
public class ForgotPassword extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   public void SendPassword(String userEmail, String userPassword){
       // Add recipient
 String to = userEmail;

// Add sender
 String from = "lawrencerosin01@gmail.com";
 final String username = "lawrencerosin01@gmail.com";//your Gmail username 
 final String password = "Darkness&Destruction";//your Gmail password

  String host = "smtp.gmail.com";

 Properties props = new Properties();
 props.put("mail.smtp.auth", "true");
 props.put("mail.smtp.starttls.enable", "true"); 
 props.put("mail.smtp.host", host);
 props.put("mail.smtp.port", "587");

// Get the Session object
  Session session = Session.getInstance(props,
  new javax.mail.Authenticator() {
  protected PasswordAuthentication getPasswordAuthentication() {
   return new PasswordAuthentication(username, password);
 }
 });

try {
 // Create a default MimeMessage object
    Message message = new MimeMessage(session);
 
    message.setFrom(new InternetAddress(from));
 
    message.setRecipients(Message.RecipientType.TO,
   InternetAddress.parse(to));
 
 // Set Subject
   message.setSubject("Your Password");
 
    // Put the content of your message
    message.setText("Your password is "+userPassword+".");

      // Send message
       Transport.send(message);
      
     }
     catch(MessagingException ex){
         
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
           PreparedStatement password=connection.prepareStatement("SELECT password FROM Accounts WHERE email_address=?");
           password.setString(1, request.getParameter("email").toUpperCase());
           ResultSet results=password.executeQuery();
           if(results.next()){
               response.getWriter().print("success");
               
              SendPassword(request.getParameter("email"), results.getString("password"));
                       
           }
           else
               response.getWriter().print("fail");
         
           connection.close();
        }
        catch(SQLException ex){
            System.out.println("Email:"+request.getParameter("email"));

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
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Emails the user's password to him/her if he/she forgets it";
    }// </editor-fold>

}
