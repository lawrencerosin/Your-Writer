<%-- 
    Document   : sign_in
    Created on : Aug 10, 2020, 12:20:26 PM
    Author     : nelli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign In</title>
        <link rel="stylesheet" type="text/css" href="CSS/appearance.css"/>
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
          <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
           <script src="JavaScript/redirection.js"></script>
        <script src="JavaScript/accounts.js"></script>
    </head>
    <body class="lightBlueBackground" onload="GoToDocument()">
    <center>
        <form>
            E-mail Address:<input type="email" id="email" name="email"/><br/>
            Password:<input type="password" id="password" name="password"/><br/>
            <button type="button" onclick="SignIn();" class="blueBackground">Sign In</button>
            <button type="button" onclick="GoToPage('create_account.jsp')" class="pinkBackground">Create Account</button> 
            <br>
            <div>
                <a href="forgot_password.jsp">Forgot Password</a>
            </div>
            <div>
              <button type="button" onclick="GoToPage('activate_account.jsp')" class="blueBackground">Activate Account</button>
               <button type="button" onclick="ResendActivationCode()" class="grayBackground">Send Activation Code</button>
            </div>
        </form>
    </center>
    
    
    </body>
</html>
