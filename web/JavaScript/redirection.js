/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function GoToPage(page){
    window.location.href=page;
}
function GoToDocument(){
     if(sessionStorage.getItem("email")!==null)
       window.location.href="document.jsp";
}
