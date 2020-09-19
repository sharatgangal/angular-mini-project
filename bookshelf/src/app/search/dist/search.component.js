"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
exports.SearchComponent = void 0;
var core_1 = require("@angular/core");
var book_1 = require("../model/book");
var SearchComponent = /** @class */ (function () {
    function SearchComponent(searchService, bookService, toastr, authenticateService, router) {
        this.searchService = searchService;
        this.bookService = bookService;
        this.toastr = toastr;
        this.authenticateService = authenticateService;
        this.router = router;
        this.books = [];
        this.book = new book_1.Book();
        this.bookService.fetchBooks();
    }
    SearchComponent.prototype.ngOnInit = function () {
    };
    /*
     After getting the Term to be searched and Search By filter
     check the term if it is undefined  Show warning to user
     else use the Google.Api to get the results as per the user input
     check if the results are having the 1.images 2.author 3.title 4.unique book-id
     */
    SearchComponent.prototype.onSearch = function () {
        var _this = this;
        //to reinitialize the array before every search
        this.books = [];
        /**
         * parameters: 1.term--->user search input
         *             2.filter-> user serach by filter
         * subscribe: imported from rxjs library of Observable type
         * searchBooks():returns Observable
         * Observable : emmits streams of data
         */
        this.searchService.searchBooks(this.term, this.filter).subscribe(function (res) {
            _this.term = undefined; //ReInitialize the user search terms to empty string
            if (res == undefined || res.totalItems == 0) {
                _this.toastr.error("please enter the valid inputs");
            }
            else {
                for (var i = 0; i < res.items.length; i++) {
                    /**
                     * storing data recieved from 3rd party api into the Book object
                     */
                    if (res.items[i].volumeInfo.authors && res.items[i].volumeInfo.title && res.items[i].volumeInfo.imageLinks) {
                        _this.book.bookAuthor = res.items[i].volumeInfo.authors;
                        _this.book.bookTitle = res.items[i].volumeInfo.title;
                        _this.book.bookImage = res.items[i].volumeInfo.imageLinks.thumbnail;
                        _this.book.bookId = res.items[i].id;
                        _this.books.push(_this.book); //storing the search results in the form of Book array
                        _this.book = new book_1.Book(); //ReIntialize the Book object 
                    }
                }
            }
            //console.log(this.books);
        }, function (err) {
            _this.toastr.error("plese enter the valid input");
            console.log(err);
        });
    };
    /**
     *
     * @param book : the book to be added to database
     */
    SearchComponent.prototype.onRecommend = function (book) {
        var _this = this;
        if (!this.authenticateService.isUserLogedin()) {
            //if the user not logged in then redirect the user to login page and display an error
            this.router.navigate(["login"]);
            this.toastr.warning("you have to login to first");
        }
        else {
            // if the user logged in then add the book to the data base
            this.bookService.addBook(book).subscribe(function (res) {
                console.log(res);
                if (res) {
                    //if the book is added then notify the user 
                    _this.toastr.success("book addded");
                }
            }, function (err) {
                //if the book is already added for the current user then notify the user.
                console.log(err);
                _this.toastr.error("book is already added");
            });
        }
    };
    SearchComponent = __decorate([
        core_1.Component({
            selector: 'app-search',
            templateUrl: './search.component.html',
            styleUrls: ['./search.component.css']
        })
    ], SearchComponent);
    return SearchComponent;
}());
exports.SearchComponent = SearchComponent;
