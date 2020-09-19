import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import { UserService } from '../services/user.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  /*
  Parameters:
  User:to store the user data 
  Countries:to store  countries details
  verifyPassword:to verify the password of the user
  */

  /*
  Constructors:UserService:is used to make the REST api request 
              :ToastrService:is used to show messages to user
              :router:is used to navigate user to diffrent page
  */
  user:User=new User();
  countries=["India","USA","UK","Germany"];
  public verifyPassword:string;
  constructor(private userService:UserService,
              private toastrService:ToastrService, 
              private router:Router) { }

  ngOnInit(): void {
  }
/**
 * onRegister():this function will make use of the service to make rest api calls
 * if the user is added then show success message and redirect the user to login
 * else show error message
 */
  onRegister()
  {
    if(this.user.password!==this.user.verifyPassword)
    {
      this.toastrService.warning("Passwords do not match")
    }
    else{
      this.user.verifyPassword=undefined;
      this.userService.addUser(this.user).subscribe(res=>{
      if(res)
      {
        console.log(res);
        this.toastrService.success("You have successfully registed");
        this.router.navigate(["login"]);
      }
    },err=>{
      console.log(err);
      this.toastrService.error("this account already has been registerd");
    })
  }
}
}
