import { Component, OnInit } from '@angular/core';
import { Book } from '../model/book';
import { SearchService } from '../services/search.service';
import {ToastrService} from 'ngx-toastr'
import { BookService } from '../services/book.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})

export class DashboardComponent implements OnInit {

  public filter:string;                   //filters: 1.any   2.title    3.author
  public books:Array<Book>=[];            //to store the search results
  public authorName:string;               //userInput String(author)
  book:Book=new Book();                   //to access the properties of each book
  public recommended:Array<Book>=[];      //all the recommended books of other users
  public favoriteBooks:Array<Book>=[];    //all the favorite books of the current user
 

  constructor( private toastr:ToastrService,
               private searchService:SearchService,
               private bookService:BookService,
               private router:Router) { 
  }

  ngOnInit(): void {
      this.bookService.getFavoriteBooks(this.book.userId).subscribe(res=>{
        if(res)
        {
          this.favoriteBooks=res;
        }
      },err=>{
        console.log(err);
      })
      this.bookService.getRecommendebooks(this.book.userId).subscribe(res=>{
        this.recommended=res;
      },err=>{
        console.log(err);
      })
  }
 
  // performing the search by author 
  public onSearch()
  {   this.filter="author";
      this.searchService.searchBooks(this.authorName,this.filter).subscribe(res=>{
      this.authorName=undefined;
         
      console.log(res);
     if(res==undefined || res.totalItems==0)
      {
        this.toastr.warning("no results found ");
      }
     else{
       //iterating the results to check for valid results 
      for(let i=0 ; i<res.items.length;i++)
      {
        if(res.items[i].volumeInfo.authors && res.items[i].volumeInfo.title &&  res.items[i].volumeInfo.imageLinks )
        { 
        this.book.bookAuthor=res.items[i].volumeInfo.authors;
        this.book.bookTitle=res.items[i].volumeInfo.title;
        this.book.bookImage=res.items[i].volumeInfo.imageLinks.thumbnail;
        this.book.bookId=res.items[i].id;
        this.book.bookReadMore=res.items[i].previewLink;
        this.books.push(this.book)
        this.book=new Book();
        }
      }
     }
      console.log(this.books);
    },err=>{
      this.toastr.error("plese enter the valid input")
      console.log(err);
    })
    this.books=[];
  }


  }




