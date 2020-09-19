import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import { UserService } from '../services/user.service';
import { AuthenticationService } from '../services/authentication.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {
/**
 * parameters:user:to access the properties of user.
 * genders:for user gender selection.
 * countries:for user country selection.
 * 
 */
  user: User = new User();
  genders=["Male","Female","Others"]
  countries=["India","USA","UK","Germany"];
  /**
   * 
   * @param userService userService:to update user:
   * @param authenticationService :to get the current user
   * @param toastr :to display message to user
   */
  constructor(private userService : UserService,
              private authenticationService:AuthenticationService,
              private toastr:ToastrService) 
  { }

  /**
   * getting the current user details
   */
  ngOnInit(): void {
    let currentUser=this.authenticationService.getCurrentUser();
    this.user.userId=currentUser;
  }
/**
 * update the user with the help of userService by passing the new user details.
 */
  updateUser() :void
  {
    console.log(this.user);
    
   this.userService.userToUpdate(this.user).subscribe(res=>{
    if(res)
    {
      console.log(res);
      
      console.log("Updated Successfully");
      this.toastr.success("updated successfully")
    }
    else{
      this.toastr.error("invalid user credentials")
    }},err=>{
      console.log(err);
      this.toastr.error("invalid user credentials")
      
    })
  
  }

}
