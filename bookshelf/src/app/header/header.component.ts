import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  /**
   * 
   * @param authentication :to get the user status
   * @param toastr :to display message to user
   */
  constructor(private authentication:AuthenticationService,private toastr:ToastrService) {
   }

  ngOnInit(): void {
  }

  /**
   * to check if the user logged in or not
   */
  isUserlogedIn():boolean
  {
    return this.authentication.isUserLogedin();
  }

  /**
   * if the user clicks on logout then we have to clear the localstorage
   */
  onLogout()
  {
    localStorage.clear();
      console.log("logout");
      this.toastr.success("logout successfully");
      // this.isLogedIn=false;
  }

}
