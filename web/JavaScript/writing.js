/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var writing, reading;
function UpdateText(){
    writing=document.getElementById("writing");
    reading=document.getElementById("reading");
    reading.innerHTML=writing.textContent;
    PutOnMultipleLines();
}
function PutOnMultipleLines(){
   
    var characterCount=0;
    var multiLineText="";
    var readingContent=reading.innerHTML;
    //Is used to only count the characters in the text displayed
   var opened=false;
    for(var position=0; position<readingContent.length; position++){
        if(readingContent.charAt(position)=='<')
            opened=true;
        else if(readingContent.charAt(position)=='>')
            opened=false;
         multiLineText+=readingContent.charAt(position);
        if(!opened){
       
          if(characterCount>20&&readingContent.charAt(position)==' '){
            multiLineText+="<br/>";
            characterCount=0;
           }
           
          else {
               
            characterCount++;
          }
        }
    }
     
    reading.innerHTML=multiLineText;
}