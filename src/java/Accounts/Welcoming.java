/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accounts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author nelli
 */
public class Welcoming extends TagSupport{
    private String email, password;
    public void setEmail(String email){
        this.email=email;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    @Override
    public int doStartTag(){
           try{
             Class.forName("com.mysql.jdbc.Driver");
           Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3307/writing", "root", "");
         PreparedStatement creation=connection.prepareStatement("SELECT first_name, last_name, email_address FROM Accounts WHERE email_address=? AND password=?");
         
         creation.setString(1, email);
         creation.setString(2, password);
         ResultSet results=creation.executeQuery();
        
         if(results.next()){
            pageContext.getOut().println("Welcome ");
         }
         
         connection.close();
        }
        catch(Exception ex ){
           
                ex.printStackTrace();
         }
        return SKIP_BODY;
    }
}
