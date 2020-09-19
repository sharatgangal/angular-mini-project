import { Component, OnInit } from '@angular/core';
import { Book } from '../model/book';
import { SearchService } from '../services/search.service';
import { BookService } from '../services/book.service';
import { ToastrService } from 'ngx-toastr';
import { AuthenticationService } from '../services/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  public filter:string;        
  public term :string;          
  public books:Array<Book>=[];
  book:Book=new Book();
 
  constructor(private searchService:SearchService,
              private bookService:BookService,
              private toastr:ToastrService,
              private authenticateService:AuthenticationService,
              private router:Router) 
  {
    this.bookService.fetchBooks();  
  }


  ngOnInit(): void {
  }
 /*
  After getting the Term to be searched and Search By filter 
  check the term if it is undefined  Show warning to user
  else use the Google.Api to get the results as per the user input
  check if the results are having the 1.images 2.author 3.title 4.unique book-id
  */
  onSearch():void
  {
    //to reinitialize the array before every search
    this.books=[];    
  /**
   * parameters: 1.term--->user search input
   *             2.filter-> user serach by filter
   * subscribe: imported from rxjs library of Observable type
   * searchBooks():returns Observable 
   * Observable : emmits streams of data 
   */
        this.searchService.searchBooks(this.term,this.filter).subscribe(res=>{
        this.term=undefined;     //ReInitialize the user search terms to empty string
     if(res==undefined || res.totalItems==0)
     {
        this.toastr.error("please enter the valid inputs")
     }
     else
     {
      for(let i=0 ; i<res.items.length;i++)
      {
        /**
         * storing data recieved from 3rd party api into the Book object 
         */
        if(res.items[i].volumeInfo.authors && res.items[i].volumeInfo.title &&  res.items[i].volumeInfo.imageLinks )
        { 
        this.book.bookAuthor=res.items[i].volumeInfo.authors;
        this.book.bookTitle=res.items[i].volumeInfo.title;
        this.book.bookImage=res.items[i].volumeInfo.imageLinks.thumbnail;
        this.book.bookId=res.items[i].id;
        this.books.push(this.book)    //storing the search results in the form of Book array
        this.book=new Book();         //ReIntialize the Book object 
        }
      }
     }
      //console.log(this.books);
    },err=>{
      this.toastr.error("plese enter the valid input")
      console.log(err);
    });
  }


  
/**
 * 
 * @param book : the book to be added to database 
 */
  onRecommend(book:Book)
  {

    if(!this.authenticateService.isUserLogedin())
    {
      //if the user not logged in then redirect the user to login page and display an error
      this.router.navigate(["login"]);
      this.toastr.warning("you have to login to first")
    }
    else{
      // if the user logged in then add the book to the data base
      this.bookService.addBook(book).subscribe(res=>{
      console.log(res);
      if(res)
      {
        //if the book is added then notify the user 
        this.toastr.success("book addded")
      }
    },err=>{
      //if the book is already added for the current user then notify the user.
      console.log(err);
      this.toastr.error("book is already added");
    })
  }
}

}
