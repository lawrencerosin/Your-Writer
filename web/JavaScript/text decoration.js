/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var fontSizeMenu, fontFamilyMenu;
var decorations;
var writing;
var alignmentBoxes;
const DESIGN_EFFECTS=["bold", "italic", "underline"];
function AddFontSizes(){
    fontSizeMenu=document.getElementById("fontSize");
    for(var size=2; size<=100; size++){
        var sizeOption=document.createElement("option");
        sizeOption.value=sizeOption.textContent=size;
        fontSizeMenu.appendChild(sizeOption);
        if(size==12)
            sizeOption.selected=true;
    }
}
var app=angular.module("myApp", []);
app.controller("fontSize", AddFontSizes);
function FindAlignment(){
    const ALIGNMENTS=["left", "center", "right"];
    alignmentBoxes=$("#alignment").find("input");
    
    for(var position=0; position<alignmentBoxes.length; position++){
       
        if(alignmentBoxes[position].checked){
           
            return ALIGNMENTS[position];
        }
    }
    //Default return
    return null;
}
function AddDecorationStyling(){
    var classesToAdd="";
    writing=document.getElementById("writing");
    var textEffects=$("#textEffects").find("input")
    fontFamilyMenu=document.getElementById("fontFamily");
    fontSize=document.getElementById("fontSize");
    $(textEffects).each(function(position, effect){
        if(effect.checked){
            
              classesToAdd+=" "+DESIGN_EFFECTS[position];
        }
    });
    var fontFamily=fontFamilyMenu.options[fontFamilyMenu.selectedIndex].value;
    var fontSize=fontSizeMenu.options[fontSizeMenu.selectedIndex].value;
    var updatedText="";
    for(var position=0; position<writing.textContent.length; position++){
      
       if(writing.selectionStart<writing.textContent.length){
       
        if(writing.selectionStart==position)
           updatedText+="<span class='"+classesToAdd+"' style='font-family:"+fontFamily+"; font-size:"+fontSize+"px; text-align:"+FindAlignment()+"'>";
        if(writing.selectionEnd==position)
             updatedText+="</span>";
     }
         updatedText+=writing.textContent.charAt(position);
    }
    if(writing.selectionStart>=writing.textContent.length)
        updatedText+="<span class='"+classesToAdd+"' style='font-family:"+fontFamily+"; font-size:"+fontSize+"; text-align:"+FindAlignment()+"'></span>";
    writing.textContent=updatedText;
}
var app=angular.module("document", []);
function AddDecorationEffects(){
    decorations=$("#decorations").find("input");
    $(decorations).each(function(position, decoration){
        decoration.addEventListener("change", AddDecorationStyling);
    });
}

