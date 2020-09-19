"use strict";
exports.__esModule = true;
exports.Book = void 0;
var Book = /** @class */ (function () {
    function Book() {
    }
    Book.prototype.setUserId = function (userId) {
        console.log(userId);
        this.userId = userId;
    };
    Book.prototype.getUserId = function () {
        return this.userId;
    };
    return Book;
}());
exports.Book = Book;
