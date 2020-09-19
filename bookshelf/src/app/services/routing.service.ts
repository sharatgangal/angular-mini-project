import { Injectable } from '@angular/core';
import { AuthenticationService } from './authentication.service';
import { Router, CanActivate} from '@angular/router';
@Injectable({
  providedIn: 'root'
})
export class RoutingService implements CanActivate {

  constructor(private authenticationService : AuthenticationService, private router : Router) { }
  canActivate()
  {
    if( localStorage.getItem('bearerToken'))
    {
      return true;
    }
    else{
    this.router.navigate(['login']);
    return false; 
  }
  }
}
