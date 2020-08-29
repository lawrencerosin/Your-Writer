/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function DefineDropDownTag(){
    var dropdowns=document.getElementsByTagName("dropdown");
    $(dropdowns).each(function(position, dropdown){
        dropdown.addEventListener("click", function(){
            var dropdownItems=$(dropdown).find("dropdownitem");
     
        //Avoids runtime error
        if(dropdownItems.length>0){
         
            //Shows the menu if it's hidden, otherwise it shows it
            if(dropdownItems[0].style.display=="none"){
                $(dropdownItems).each(function(position1, dropdownItem){
                    dropdownItem.style.display="block";
                });
            }
            else{
                $(dropdownItems).each(function(position1, dropdownItem){
                   dropdownItem.style.display="none"; 
                });
            }
        }
      });
     }
    );
}


