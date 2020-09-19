import { Component, OnInit } from '@angular/core';
import { User } from '../model/user'; 
import { ToastrService } from 'ngx-toastr';
import { AuthenticationService} from '../services/authentication.service'
import { Router } from '@angular/router';
import { Book } from '../model/book';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  book:Book=new Book();
  user:User=new User();

  constructor(private toastr:ToastrService,private authenticationService:AuthenticationService,private router:Router) { }

  bearerToken : any;
  ngOnInit(): void {
  }

  onLogin():void
  {
    {
      this.authenticationService.validateUser(this.user.userId,this.user.password).subscribe(response=>{
      if(response)
      {
        console.log(response);
        console.log(response[`token`]);
        this.bearerToken=response[`token`];
        this.authenticationService.setBearerToken(this.bearerToken);
        this.book.setUserId(this.user.userId);
        this.toastr.success("login Successfull")
        
        //to store the current user i local storage
        this.authenticationService.setCurrentUser(this.user.userId)

        this.authenticationService.setIsUserLogedin(true);
        // this.router.navigate['dashboard'];
        this.router.navigate([`search`]);
      }
      }, error=>{
         this.toastr.error("Invalid UserId or Password");
         console.log(error);
      });
     }
  }
  userNavigate() : void
  {
    this.router.navigate([`register`]);
  }
}
