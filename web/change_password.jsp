<%-- 
    Document   : change_password
    Created on : Aug 27, 2020, 10:28:07 AM
    Author     : nelli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Your Password</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
        <link rel="stylesheet" type="text/css" href="CSS/positions.css"/>
        <link rel="stylesheet" type="text/css" href="CSS/appearance.css"/>
        <script src="JavaScript/accounts.js"></script>
        <script src="JavaScript/redirection.js"></script>
    </head>
    <body class="orangeBackground">
        <button type="button" onclick="GoToPage('document.jsp')" class="redBackground whiteText">Back</button>
        <form ng-app="changePassword" ng-controller="change" class="horizontalCenter">
            <span class="whiteText">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Your Info</span><br/>
            <input type="email" id="email" readonly="true" ng-model="email" value="{{email}}"/><br/>
            <input type="password" id="oldPassword" placeholder="Current Password" required/><br/>
            <input type="password" id="newPassword" placeholder="Desired Password" required/><br>
            <input type="password" id="confirmNewPassword" placeholder="Confirm Desired Password" required/><br/>
            <button onclick="ChangePassword()" class="yellowBackground">Change</button>
            <button type="button" onclick="GoToPage('forgot_password.jsp')" class="redBackground whiteText">Forgot Password</button>
        </form>
    </body>
</html>
