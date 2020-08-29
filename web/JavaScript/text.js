/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$("#writing").change(function(){
var app=angular.module("document", []);
app.directive("pageDivide", function(){
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
            template: "<page></page>"
        };
    }
       
  });
});
    
