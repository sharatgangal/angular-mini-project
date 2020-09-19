import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs'
import { ToastrService } from 'ngx-toastr';

@Injectable()
export class SearchService {

  public baseUrl="https://www.googleapis.com/books/v1/volumes?";
  myKey="AIzaSyD4jmeqeCa45e_RBqnSRTE6xbNXu1Pv8kg"
  public searchResults:any;

  constructor(private httpClient : HttpClient , private toastr:ToastrService) { }

  public searchBooks(term,searchBy):Observable<any>
  {
    console.log(term);
    console.log(searchBy);
    if(term==undefined)
    {
      this.toastr.error("serach filed can't be empty")
    }
    else{
     if(searchBy=="author" && term!=undefined) 
     {
       console.log(searchBy);
       console.log(term);
       
       
       console.log("in the search by author");
       
    return this.httpClient.get(`${this.baseUrl}q=${term}+inauthor:&key=${this.myKey}`);
     }
     if(searchBy=="title" &&  term!=undefined) 
     {
    return this.httpClient.get(`${this.baseUrl}q=${term}+intitle:&key=${this.myKey}`)
     }
     if(searchBy="any" && term!=undefined ||searchBy==undefined && term!=undefined)
     {
       console.log("i am in the serch method for any ");
       
      return this.httpClient.get(`${this.baseUrl}q=${term}`)
     }
  }
}



}
