import { Component, OnInit } from '@angular/core';
import { BookService } from '../services/book.service';
import { Book } from '../model/book';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-recommended',
  templateUrl: './recommended.component.html',
  styleUrls: ['./recommended.component.css']
})
export class RecommendedComponent implements OnInit {

  books:Array<Book>=[];
  book:Book=new Book();
  constructor(private bookService :BookService, 
              private toastr:ToastrService) 
   {
    this.bookService.fetchBooks();
   }

  ngOnInit(): void {
    this.bookService.fetchBooks();
    this.bookService.getBooks().subscribe(res=>{
      console.log(res);
      this.books=res;      
    },err=>{
      console.log(err);
    })
  }

  onUnRecommend(book)
  {
  console.log("unrecommend button clicked");
  this.toastr.success("book unrecommended")
  this.bookService.deleteBook(book._id).subscribe(res=>{
    this.bookService.fetchBooks();
  },err=>{
    console.log(err);
  })
  }
}
