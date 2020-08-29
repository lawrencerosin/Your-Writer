/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function CreateAccount(){ 
    var firstName=document.getElementById("firstName").value;
    var lastName=document.getElementById("lastName").value;
    var email=document.getElementById("email").value;
    var password=document.getElementById("password").value;
    var confirmedPassword=document.getElementById("confirmPassword").value;
   if(password==confirmedPassword){
    $.ajax({
        url:"createAccount",
        type:"POST",
        data:{"firstName":firstName, "lastName":lastName, "email":email, "password":password},
        success: function(){
            alert("You have successfully created your account.");
            window.location.href="sign_in.jsp";
        },
        error:function(){
            alert("You were unable to create an account. It's possible that there is already an account with the e-mail address you've entered.");
        },
        complete: function(){
            alert("hello");
        }
    });
  }
  else
      alert("The first and second password don't match.");
 
}
var app=angular.module("document", []);
app.controller("validate", ValidateSignIn);
//Is used to determine if the user entered existing credentials
function StartsWithWelcome(text){
    var position=0;
    const WELCOME="Welcome";
    while(position<text.length&&position<WELCOME.length&&WELCOME.charAt(position)==text.charAt(position))
        position++;
 
    if(position>=WELCOME.length)
        return true;
    else
        return false;
}
function ValidateSignIn(){
  try{  var email=sessionStorage.getItem("email");
    var password=sessionStorage.getItem("password");
   
    $.ajax(
     {
          url:"signIn",
          type:"POST",
          data:{"email":email, "password":password},
          success: function(data){
      
           //If the user failed to sign in
           if(!StartsWithWelcome(data)){
              //If the user entered incorrect credentials
              if(data.length==0)
                  alert("There is no account with the e-mail address and password you've entered.");
             //If the user entered correct credentials, but the account was not activated yet
              else
                  alert("The account exists, but was not activated. If you'd like, you can click \"Send Activation Code\" on the sign in page, to resend the code.");
              window.location.href="sign_in.jsp";
              //Avoids going back and forth from the sign in page and document page
              sessionStorage.removeItem("email");
              sessionStorage.removeItem("password");
           }
          }
     });
   }
   //If the user didn't even attempt to sign in
   catch(ex){
       alert("You didn't sign in.");
       window.location.href="sign_in.jsp";
   }
}
function ActivateAccount(){
    var email=document.getElementById("email").value;
    var code=document.getElementById("code").value;
    $.ajax({
       url: "activationCode",
       type: "POST",
       data:{"email":email, "code":code},
       success: function(data){
           if(data=="success"){
               alert("You have successfully activated your account.");
               //Goes to the sign in page
               window.location.href="sign_in.jsp";
           }
           else
               alert("You've entered the wrong e-mail address and/or activation code.");
       },
       error: function(){
           alert("There's been a problem trying to activate your account.");
       }
    });
}
function ResendActivationCode(){
    var email=prompt("What is your e-mail address?");
    
    $.ajax({
       url: "activationCode",
       type: "GET",
       data: {"email":email},
       success: function(data){
           if(data=="success")
               alert("Your code has been sent.");
           else
               alert("The e-mail address you've entered doesn't exist.");
       }
    });
}
function SignIn(){
    var email=document.getElementById("email").value;
    var password=document.getElementById("password").value;

    
           sessionStorage.setItem("email", email);
           sessionStorage.setItem("password", password);
           window.location.href="document.jsp";
       
}
function SignOut(){
    sessionStorage.removeItem("email");
    sessionStorage.removeItem("password");
    window.location.href="sign_in.jsp";
}
function ChangePassword(){
    var email=sessionStorage.getItem("email");
    var oldPassword=document.getElementById("oldPassword").value;
    var newPassword=document.getElementById("newPassword").value;
    var confirmedNewPassword=document.getElementById("confirmNewPassword").value;
    if(newPassword==confirmedNewPassword){
        $.ajax({
           url: "changePassword",
           type: "POST",
           data:{"email":email, "oldPassword":oldPassword, "newPassword":newPassword},
           success: function(data){alert(data);
               if(data=="success"){
                   alert("You have successfully changed your password");
                   window.location.href="document.jsp";
               }
               else
                   alert("You've entered the wrong password.");
           },
           error: function(){
               alert("There's been a problem trying to change your password.");
           }
        });
    }
    else
        alert("Your desired password doesn't match it's confirmation.");
}
var app1=angular.module("changePassword", []);
app1.controller("change", function($scope){
   if(sessionStorage.getItem("email")!==null)
     $scope.email=sessionStorage.getItem("email");
  else{
      alert("You didn't sign in. You must sign in to chane your password");
      window.location.href="sign_in.jsp";
  }
});
