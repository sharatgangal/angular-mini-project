import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable, BehaviorSubject} from 'rxjs'
import { Book } from '../model/book'
import { tap } from 'rxjs/operators';
import { AuthenticationService } from './authentication.service';



@Injectable()
export class BookService {
  
  books:Array<Book>;
  bookSubject:BehaviorSubject<Array<Book>>
  book:Book=new Book()

  constructor(private httpClient : HttpClient,private authenticationService:AuthenticationService) {
    this.bookSubject=new BehaviorSubject([]);
   }


   public fetchBooks()
   {
    const bearerToken = this.authenticationService.getBearerToken();
    console.log(bearerToken);
    const currentUser=this.authenticationService.getCurrentUser();
    console.log("in the featch books ()");
    console.log(currentUser);
    return this.httpClient.get<Array<Book>>(`http://localhost:8888/api/v1/bookapp/getFavoriteBooks/${currentUser}`,{
      headers: new HttpHeaders().set("Authorization",`Bearer ${bearerToken}`)
    }).subscribe(res=>{
      this.books=res;
      this.bookSubject.next(this.books);
    },err=>{
      console.log(err);
      
    });
   }

   public getBooks() : BehaviorSubject<Array<Book>>
    {
    return this.bookSubject; 
    }


    public addBook(book):Observable<Book>
    {
      const bearerToken = this.authenticationService.getBearerToken();
      book.userId=this.authenticationService.getCurrentUser();
      console.log(book);
      return this.httpClient.post<Book>("http://localhost:8888/api/v1/bookapp/addbook",book,{
        headers: new HttpHeaders().set("Authorization",`Bearer ${bearerToken}`)
      }).pipe(tap(addedBook=>{
        this.books.push(addedBook);
        this.bookSubject.next(this.books)
      }))
    }

    public deleteBook(bookId):Observable<any>
    { const bearerToken = this.authenticationService.getBearerToken();
      console.log("before deleteing book");
      console.log(bookId);
      
      return this.httpClient.delete(`http://localhost:8888/api/v1/bookapp/deleteBook/${bookId}`,{
        headers: new HttpHeaders().set("Authorization",`Bearer ${bearerToken}`)
      });
    }

    public getFavoriteBooks(userId):Observable<any>
    {
      console.log("the user id to in the favorite is");
      console.log(userId);
      
      const currentUser=this.authenticationService.getCurrentUser();
      const bearerToken = this.authenticationService.getBearerToken();
      return this.httpClient.get(`http://localhost:8888/api/v1/bookapp/getFavoriteBooks/${currentUser}`,{
        headers: new HttpHeaders().set("Authorization",`Bearer ${bearerToken}`)
      })
    }
    public getRecommendebooks(userId):Observable<any>
    {
      const currentUser=this.authenticationService.getCurrentUser();
      const bearerToken = this.authenticationService.getBearerToken();
      return this.httpClient.get(`http://localhost:8888/api/v1/bookapp//getRecommendedBooks/${currentUser}`,{
        headers: new HttpHeaders().set("Authorization",`Bearer ${bearerToken}`)
      })
    }
  }

