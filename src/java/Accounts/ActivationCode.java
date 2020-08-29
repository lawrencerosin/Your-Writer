/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accounts;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author nelli
 */
public class ActivationCode{
    private String code;
    public ActivationCode(){
      
        //Initializes code to empty string
        code="";
        for(int cycle=1; cycle<=10; cycle++){
            Random randomNumberGenerator=new Random();
            int randomNumber=randomNumberGenerator.nextInt(10);
            code+=Integer.toString(randomNumber);
        }
        
    }
    public void SetActivationCode(String code){
        this.code=code;
    }
    public void SendEmailActivationCode(String email, HttpServletResponse response/*Is used to inform the user whether or not the email was sent*/){
 // Add recipient
 String to = email;

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
   message.setSubject("Activation Code");
 
    // Put the content of your message
    message.setText("Your activation code is "+code+".");

      // Send message
       Transport.send(message);
      
     }
     catch(MessagingException ex){
         
       }
    }

    
    public String GetActivationCode(){
        return code;
    }
}
