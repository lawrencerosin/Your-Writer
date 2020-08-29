<%-- 
    Document   : document
    Created on : Aug 11, 2020, 2:57:32 PM
    Author     : nelli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/welcome.tld" prefix="acc"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Document</title>
        <link rel="stylesheet" type="text/css" href="CSS/gui.css"/>
        <link rel="stylesheet" type="text/css" href="CSS/appearance.css"/>
        <link rel="stylesheet" type="text/css" href="CSS/positions.css"/>
        <link rel="stylesheet" type="text/css" href="CSS/text decoration.css"/>
          <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
        <script src="JavaScript/accounts.js"></script>
        <script src="JavaScript/text.js"></script>
        <script src="JavaScript/text decoration.js"></script>
        <script src="JavaScript/gui.js"></script>
        <script src="JavaScript/documents.js"></script>
        <script src="JavaScript/redirection.js"></script>
        
    </head>
    <body ng-app="myApp" ng-controller="fontSize" class="lightGreenBackground" onload="ValidateSignIn(); AddDecorationEffects(); DefineDropDownTag()">
        <acc:welcome email="" password=""/>
       <dropdown>Documents
           <dropdownitem onclick="NewDocument()">New</dropdownitem>
           <dropdownitem onclick="Open()">Open</dropdownitem>
           <dropdownitem onclick="DeleteDocument()">Delete Document</dropdownitem>
           <dropdownitem onclick="ViewDocuments()">View Documents</dropdownitem>
           <dropdownitem onclick="Save()">Save</dropdownitem>
           <dropdownitem onclick="SaveAs()">Save As</dropdownitem>
       </dropdown>
       
    <span class="right">
        <button type="button" onclick="GoToPage('change_password.jsp')" class="yellowBackground">Change Password</button>
         <button onclick="SignOut()" class="redBackground">Sign Out</button>
    </span>
    <nav id="decorations">
        <span id="textEffects">
       Bold:<input type="checkbox" id="bold"/>   
       
       Italic:<input type="checkbox" id="italics"/>
       Underline:<input type="checkbox" id="underline"/>
        </span>
       <form id="alignment">
           Left<input type="radio" name="alignment" id="left" checked/>
           Center<input type="radio" name="alignment" id="center"/>
           Right<input type="radio" name="alignment" id="right"/>
       </form>
       Font Family:
       <select id="fontFamily" onchange="AddDecorationStyling()">
         <option value="Calibri">Calibri</option>
         <option value="Times New Roman">Times New Roman</option>
         <option value="Arial">Arial</option>
       </select>
       Font Size:<select id="fontSize" onchange="AddDecorationStyling()"></select>
     </nav>
        <span id="welcoming">${welcoming}</span>
        <textarea id="writing"></textarea>
        <div id="reading" page-divide>
            
        </div>
        <script>
    
var app=angular.module("document", []);
app.directive("pageDivide", function(){
    $("#writing").change(function(){
    var text=$("#writing").text();
    if(text.length>0){
        var pageNumber=1;
        //Is used to exclude the content in the outer html
        var opened=false;
        var formattedText="<page>";
        var textPosition=0;
        for(var position=0; position<text.length; position++){
            if(text.charAt(position)=='<')
                opened=true;
            else if(text.charAt(position)=='>')
                opened=false;
            if(!opened)
                textPosition++;
            formattedText+=text.charAt(position);
            //Adds the text to the next page, while avoiding breaking words
            if(textPosition>pageNumber*4000&&text.charAt(position)==' '){
                formattedText+="</page><page>";
                pageNumber++;
            }
                
        }
        //Closes the last page tag
        formattedText+="</page>";
        $("#reading").html(formattedText);
        return{
          template: formattedText  
        };
    }
    else{
        $("#reading").html("<page></page>");
        return{
            template: "<page>h</page>"
        };
    }
       
  });
 });
    
</script>
    </body>
</html>
