<%-- 
    Document   : create_account
    Created on : Aug 9, 2020, 12:00:10 PM
    Author     : nelli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" type="text/css" href="CSS/decorations.css"/>
         <link rel="stylesheet" type="text/css" href="CSS/appearance.css"/>
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
         <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
         <script src="JavaScript/redirection.js"></script>
         <script src="JavaScript/accounts.js"></script>
        
    </head>
    <body class="orangeBackground" onload="GoToDocument()">
        <form>
          <h1 class="yellowText">Your Info</h1>
          First Name:<input type="text" id="firstName" name="firstName" required/><br/>
          Last Name:<input type="text" id="lastName" name="lastName" required/><br/>
          E-mail Address:<input type="email" id="email" name="email" required/><br/>
          Password:<input type="password" id="password" name="password" required/><br/>
          Confirm Password:<input type="password" id="confirmPassword" name="confirmPassword" required/><br/>
          <button  onclick="CreateAccount()" class="yellowBackground">Create Account</button>
          <button type="button" onclick="GoToPage('sign_in.jsp')" id="greenBackground">Sign In</button>
        </form>
    </body>
</html>
