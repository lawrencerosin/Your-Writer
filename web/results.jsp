<%-- 
    Document   : results
    Created on : Nov 20, 2019, 8:30:06 PM
    Author     : Nellie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Results</title>
    </head>
    <body>
        <%!
         //Is used to avoid the error of a single quote terminating a string early
          String IncludeSingleQuote(String text){
              String withSingleQuote="";
              for(int position=0; position<text.length(); position++){
                  withSingleQuote+=text.charAt(position);
                  //Adds another single quote to make it so the single quote will be part of the string, instead of terminating it
                  if(text.charAt(position)=='\'')
                      withSingleQuote+="\'";
               }
              return withSingleQuote;
          }
        %>
        <div id="matches">
       <%
           try{Class.forName("com.mysql.jdbc.Driver");
           String searchText=IncludeSingleQuote(request.getParameter("searchItem"));
           Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/novels", "root", "");
           Statement statement=connection.createStatement();
           ResultSet numberOfColumns=statement.executeQuery("Select Count(*) as row_count From Top_Novels "+"Where Match(title, author, year_of_publication, summary) Against('"+IncludeSingleQuote(request.getParameter("searchItem"))+"*' in boolean mode)");
           numberOfColumns.first();
           int rows=numberOfColumns.getInt("row_count");
           numberOfColumns.close();
           ResultSet results=statement.executeQuery("Select title, author, year_of_publication, summary From Top_Novels "
                   + "Where Match(title, author, year_of_publication, summary) Against('"+searchText+"*' In Boolean Mode)");
           //results.first();
           if(rows>0){
              out.println("<table>");
              out.println("<tr><th colspan='4'>Matching Results</th></tr>");
              out.println("<tr><th>Title</th><th>Author</th><th>Year of Publication</th><th>Summary</th></tr>");
              while(results.next()){
                  out.print("<tr>");
                  for(int columnNumber=1; columnNumber<=results.getMetaData().getColumnCount(); columnNumber++)
                      out.print("<td>"+results.getString(columnNumber)+"</td>");
                  out.print("<tr>");
              }
              out.println("</table>");
             
          }
          else
              out.println("No results found.");
         }
         catch(Exception ex){
             out.println(ex.getMessage());
         }
       %>
        </div>
    </body>
</html>
