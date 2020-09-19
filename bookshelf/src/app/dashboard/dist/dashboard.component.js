"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
exports.DashboardComponent = void 0;
var core_1 = require("@angular/core");
var book_1 = require("../model/book");
var DashboardComponent = /** @class */ (function () {
    function DashboardComponent(toastr, searchService, bookService, router) {
        this.toastr = toastr;
        this.searchService = searchService;
        this.bookService = bookService;
        this.router = router;
        this.books = []; //to store the search results
        this.book = new book_1.Book(); //to access the properties of each book
        this.recommended = []; //all the recommended books of other users
        this.favoriteBooks = []; //all the favorite books of the current user
    }
    DashboardComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.bookService.getFavoriteBooks(this.book.userId).subscribe(function (res) {
            if (res) {
                _this.favoriteBooks = res;
            }
        }, function (err) {
            console.log(err);
        });
        this.bookService.getRecommendebooks(this.book.userId).subscribe(function (res) {
            _this.recommended = res;
        }, function (err) {
            console.log(err);
        });
    };
    // performing the search by author 
    DashboardComponent.prototype.onSearch = function () {
        var _this = this;
        this.filter = "author";
        this.searchService.searchBooks(this.authorName, this.filter).subscribe(function (res) {
            _this.authorName = undefined;
            console.log(res);
            if (res == undefined || res.totalItems == 0) {
                _this.toastr.warning("no results found ");
            }
            else {
                //iterating the results to check for valid results 
                for (var i = 0; i < res.items.length; i++) {
                    if (res.items[i].volumeInfo.authors && res.items[i].volumeInfo.title && res.items[i].volumeInfo.imageLinks) {
                        _this.book.bookAuthor = res.items[i].volumeInfo.authors;
                        _this.book.bookTitle = res.items[i].volumeInfo.title;
                        _this.book.bookImage = res.items[i].volumeInfo.imageLinks.thumbnail;
                        _this.book.bookId = res.items[i].id;
                        _this.book.bookReadMore = res.items[i].previewLink;
                        _this.books.push(_this.book);
                        _this.book = new book_1.Book();
                    }
                }
            }
            console.log(_this.books);
        }, function (err) {
            _this.toastr.error("plese enter the valid input");
            console.log(err);
        });
        this.books = [];
    };
    DashboardComponent = __decorate([
        core_1.Component({
            selector: 'app-dashboard',
            templateUrl: './dashboard.component.html',
            styleUrls: ['./dashboard.component.css']
        })
    ], DashboardComponent);
    return DashboardComponent;
}());
exports.DashboardComponent = DashboardComponent;
