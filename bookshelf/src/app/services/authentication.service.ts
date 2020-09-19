import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {


  private status:boolean;
  private isUserlogedIn=false;

  private authUrl: string;
  currentUser:string;

  /**
   * 
   * @param httpClient :to communicate with backend services
   */
  constructor(private httpClient : HttpClient) { 
     this.authUrl='http://localhost:9999/api/v1/user/login';
  }
//to set the 
  setIsUserLogedin(islogedIn:boolean)
  {
    this.isUserlogedIn=islogedIn;
  }
  getIsUserLogedIn()
  {
    return this.isUserlogedIn;
  }

  isUserLogedin():boolean
  {
    if(localStorage.getItem('user'))
    {
      return true;
    }
    else{
      return false;
    }
  }

 
    setBearerToken(token)
    {
      localStorage.setItem('bearerToken',token);
    }

    getBearerToken()
    {
      return localStorage.getItem('bearerToken');
    }

    setCurrentUser(user:string)
    {
      localStorage.setItem('user',user)
    }

    getCurrentUser()
    {
      return localStorage.getItem('user');
    }

  validateUser(emailId : string,password:string) : Observable<User>
  {
    return this.httpClient.get<User>(`${this.authUrl}/${emailId},${password}`);
  }
}
