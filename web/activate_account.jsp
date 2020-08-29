<%-- 
    Document   : activate_account
    Created on : Aug 25, 2020, 12:42:35 PM
    Author     : nelli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Activate Your Account</title>
        <link rel="stylesheet" type="text/css" href="CSS/appearance.css"/>
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
          <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
        <script src="JavaScript/accounts.js"></script>
        <script src="JavaScript/redirection.js"></script>
    </head>
    <body class="blueBackground" onload="GoToDocument()">
      <center>
        <form>
            <h1 class="whiteText">Enter the information below</h1>
            <input type="email" id="email" placeholder="E-mail Address" required/><br/>
            <input type="text" id="code" placeholder="Activation Code" required/><br/>
            <button onclick="ActivateAccount()" id="aquaBackground">Activate Account</button>
        </form
     </center>
    </body>
</html>
