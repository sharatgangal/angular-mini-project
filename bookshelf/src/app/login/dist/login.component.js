"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
exports.LoginComponent = void 0;
var core_1 = require("@angular/core");
var user_1 = require("../model/user");
var book_1 = require("../model/book");
var LoginComponent = /** @class */ (function () {
    function LoginComponent(toastr, authenticationService, router) {
        this.toastr = toastr;
        this.authenticationService = authenticationService;
        this.router = router;
        this.book = new book_1.Book();
        this.user = new user_1.User();
    }
    LoginComponent.prototype.ngOnInit = function () {
    };
    LoginComponent.prototype.onLogin = function () {
        var _this = this;
        {
            this.authenticationService.validateUser(this.user.userId, this.user.password).subscribe(function (response) {
                if (response) {
                    console.log(response);
                    console.log(response["token"]);
                    _this.bearerToken = response["token"];
                    _this.authenticationService.setBearerToken(_this.bearerToken);
                    _this.book.setUserId(_this.user.userId);
                    _this.toastr.success("login Successfull");
                    //to store the current user i local storage
                    _this.authenticationService.setCurrentUser(_this.user.userId);
                    _this.authenticationService.setIsUserLogedin(true);
                    // this.router.navigate['dashboard'];
                    _this.router.navigate(["search"]);
                }
            }, function (error) {
                _this.toastr.error("Invalid UserId or Password");
                console.log(error);
            });
        }
    };
    LoginComponent.prototype.userNavigate = function () {
        this.router.navigate(["register"]);
    };
    LoginComponent = __decorate([
        core_1.Component({
            selector: 'app-login',
            templateUrl: './login.component.html',
            styleUrls: ['./login.component.css']
        })
    ], LoginComponent);
    return LoginComponent;
}());
exports.LoginComponent = LoginComponent;
