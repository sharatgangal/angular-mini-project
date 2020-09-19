import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable, BehaviorSubject} from 'rxjs'
import { ToastrService } from 'ngx-toastr';
import { User } from '../model/user';
import { tap } from 'rxjs/operators';
import {AuthenticationService} from '../services/authentication.service'
@Injectable({
  providedIn: 'root'
})
export class UserService {

  user : Array<User>;
  userSubject : BehaviorSubject<Array<User>>;

  constructor(private httpClient : HttpClient, private authenticationService : AuthenticationService) { 
    this.userSubject= new BehaviorSubject([]);
  }

  addUser(user : User ) : Observable<User>
  { 
   return this.httpClient.post<User>("http://localhost:9999/api/v1/user/addUser",user)
  }

  userToUpdate(user : User) : Observable<any>
  {
    // return this.httpClient.post<Book>("http://localhost:9090/api/v1/book/addBook",book).pipe(tap(addedBook=>{    // for MongoDB
    
    return this.httpClient.put<User>(`http://localhost:9999/api/v1/user/updateUser/${user.userId}`, user);
  }
}
