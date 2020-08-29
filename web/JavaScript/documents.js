/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 
*/
//Is used to avoid problems because the title of the document and content of the document are passed together
function NoPeriod(text){
    for(var position=0; position<text.length; position++)
        if(text.charAt(position)=='.')
            return false;
    return true;
}
function NewDocument(){
    document.getElementById("writing").value="";
    var checkboxes=$("#decorations").find("input");
    //Unchecks all effects
    $(checkboxes).each(function(position, checkbox){
        checkbox.checked=false;
    });
    document.getElementById("left").checked=true;
    document.getElementById("fontFamily").selectedIndex=0;
    document.getElementById("fontSize").selectedIndex=10;
    document.title="New Document";
    //The title is removed from storage
    sessionStorage.removeItem("title");
}
 function Open(){
    var title=prompt("What is the name of the file you want to open?");
    var email=sessionStorage.getItem("email");
    $.get("documents", {"email":email, "title":title},
        function(data){
           if(data.length>0){
               $("#writing").text(data.toString().substring(data.indexOf('.')+1));
           document.title=title;
               //To know there's a title
               sessionStorage.setItem("title", title);
           }
           else
               alert("You don't have a document named "+title+".");
       }
       );
 }
 function DeleteDocument(){
     var title=prompt("What is the title of the document you want to delete?");
     var email=sessionStorage.getItem("email");
     $.ajax({
        url: "removeDocument",
        type: "POST",
        data: {"email":email, "title": title},
        success: function(data){
            if(data=="exists"){
                alert("You have successfully deleted "+title+".");
                if(sessionStorage.getItem("title")===title)
                    NewDocument();//Resets because the document doesn't exist anymore
            }
            else
                alert("You don't have a document named "+title+".");
        }
     });
 }
function ViewDocuments(){
    var email=sessionStorage.getItem("email");
   $.ajax({
       url:"showDocuments",
       type:"POST",
       data: {"email":email},
       success: function(data){
           alert(data);
       }, 
       error: function(xhr){
           alert(xhr.status);
       }
   })
}
function IsTheLetter(character, letter){
    if(character.charCodeAt(0)==letter.charCodeAt(0)||character.charCodeAt(0)==letter.charCodeAt(0)-32)
        return true;
    else
        return false;
}
function Save(){
    if(sessionStorage.getItem("title")!==null){
        $.ajax(
           {
              url: "save",      
              type:"POST",
              data: {"title":sessionStorage.getItem("title"), "email":sessionStorage.getItem("email"), "content": document.getElementById("reading").innerHTML},
              success: function(){
                  alert("You have successfully saved "+sessionStorage.getItem("title")+".");
              },
              error: function(){
                  alert("You were unable to save "+title+".");
              }
           }
        );
    }
    else
        SaveAs();
    
} 
function SaveAs(){
    var title=prompt("What do you want to name the document?");
    var email=sessionStorage.getItem("email");
    var content=document.getElementById("writing").textContent;
   
    $.ajax({
        url: "documents",
        type: "POST", 
        data: {"title":title, "email":email, "content":content},
        success: function(data){
            if(data=="error"){
              
                var overwrite=prompt("You already have the document "+title+". If you'd like to overwrite it, enter \"y\". Otherwise enter \"n\".");
                while(!IsTheLetter(overwrite, 'y')&&!IsTheLetter(overwrite, 'n')){
                switch(overwrite){
                    case 'y': case 'Y': Save(); break;
                    case 'n': case 'N': break;
                    default:
                        overwrite=prompt("You must type in \"y\" or \"n\"");
                        break;
                }
              }
              
            }
            else{
                sessionStorage.setItem("title", title);//So the current document will be known
                document.title=title;
            }
        }
    });
}
