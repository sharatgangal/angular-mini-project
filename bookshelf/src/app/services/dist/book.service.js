"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
exports.BookService = void 0;
var core_1 = require("@angular/core");
var http_1 = require("@angular/common/http");
var rxjs_1 = require("rxjs");
var book_1 = require("../model/book");
var operators_1 = require("rxjs/operators");
var BookService = /** @class */ (function () {
    function BookService(httpClient, authenticationService) {
        this.httpClient = httpClient;
        this.authenticationService = authenticationService;
        this.book = new book_1.Book();
        this.bookSubject = new rxjs_1.BehaviorSubject([]);
    }
    BookService.prototype.fetchBooks = function () {
        var _this = this;
        var bearerToken = this.authenticationService.getBearerToken();
        console.log(bearerToken);
        var currentUser = this.authenticationService.getCurrentUser();
        console.log("in the featch books ()");
        console.log(currentUser);
        return this.httpClient.get("http://localhost:8888/api/v1/bookapp/getFavoriteBooks/" + currentUser, {
            headers: new http_1.HttpHeaders().set("Authorization", "Bearer " + bearerToken)
        }).subscribe(function (res) {
            _this.books = res;
            _this.bookSubject.next(_this.books);
        }, function (err) {
            console.log(err);
        });
    };
    BookService.prototype.getBooks = function () {
        return this.bookSubject;
    };
    BookService.prototype.addBook = function (book) {
        var _this = this;
        var bearerToken = this.authenticationService.getBearerToken();
        book.userId = this.authenticationService.getCurrentUser();
        console.log(book);
        return this.httpClient.post("http://localhost:8888/api/v1/bookapp/addbook", book, {
            headers: new http_1.HttpHeaders().set("Authorization", "Bearer " + bearerToken)
        }).pipe(operators_1.tap(function (addedBook) {
            _this.books.push(addedBook);
            _this.bookSubject.next(_this.books);
        }));
    };
    BookService.prototype.deleteBook = function (bookId) {
        var bearerToken = this.authenticationService.getBearerToken();
        console.log("before deleteing book");
        console.log(bookId);
        return this.httpClient["delete"]("http://localhost:8888/api/v1/bookapp/deleteBook/" + bookId, {
            headers: new http_1.HttpHeaders().set("Authorization", "Bearer " + bearerToken)
        });
    };
    BookService.prototype.getFavoriteBooks = function (userId) {
        console.log("the user id to in the favorite is");
        console.log(userId);
        var currentUser = this.authenticationService.getCurrentUser();
        var bearerToken = this.authenticationService.getBearerToken();
        return this.httpClient.get("http://localhost:8888/api/v1/bookapp/getFavoriteBooks/" + currentUser, {
            headers: new http_1.HttpHeaders().set("Authorization", "Bearer " + bearerToken)
        });
    };
    BookService.prototype.getRecommendebooks = function (userId) {
        var currentUser = this.authenticationService.getCurrentUser();
        var bearerToken = this.authenticationService.getBearerToken();
        return this.httpClient.get("http://localhost:8888/api/v1/bookapp//getRecommendedBooks/" + currentUser, {
            headers: new http_1.HttpHeaders().set("Authorization", "Bearer " + bearerToken)
        });
    };
    BookService = __decorate([
        core_1.Injectable()
    ], BookService);
    return BookService;
}());
exports.BookService = BookService;
