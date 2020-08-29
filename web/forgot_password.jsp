<%-- 
    Document   : forgot_password
    Created on : Aug 25, 2020, 5:23:48 PM
    Author     : nelli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Password</title>
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <link rel="stylesheet" type="text/css" href="CSS/appearance.css"/>
        <link rel="stylesheet" type="text/css" href="CSS/positions.css"/>
        <script src="JavaScript/accounts.js"></script>
        <script src="JavaScript/redirection.js"></script>
    </head>
    <body id="aquaBackground">
        <form id="forgotPassword" class="horizontalCenter">
            <span class="blueText">Enter your e-mail address,<br/>
                 and your password will be emailed to you</span>
            <br/>
            <input type="email" id="email" placeholder="E-mail Address" required /><br/>
            <button onclick="SendPassword()" class="pinkBackground">Email Password</button>
            <button type="button" onclick="GoToPage('sign_in.jsp')" class="blueBackground">Sign In</button>
        </form>
        <script>
            $("#forgotPassword").submit(function(e){
                e.preventDefault();
            });
            </script>
    </body>
</html>
