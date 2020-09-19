"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
exports.RegisterComponent = void 0;
var core_1 = require("@angular/core");
var user_1 = require("../model/user");
var RegisterComponent = /** @class */ (function () {
    function RegisterComponent(userService, toastrService, router) {
        this.userService = userService;
        this.toastrService = toastrService;
        this.router = router;
        /*
        Parameters:
        User:to store the user data
        Countries:to store  countries details
        verifyPassword:to verify the password of the user
        */
        /*
        Constructors:UserService:is used to make the REST api request
                    :ToastrService:is used to show messages to user
                    :router:is used to navigate user to diffrent page
        */
        this.user = new user_1.User();
        this.countries = ["India", "USA", "UK", "Germany"];
    }
    RegisterComponent.prototype.ngOnInit = function () {
    };
    /**
     * onRegister():this function will make use of the service to make rest api calls
     * if the user is added then show success message and redirect the user to login
     * else show error message
     */
    RegisterComponent.prototype.onRegister = function () {
        var _this = this;
        if (this.user.password !== this.user.verifyPassword) {
            this.toastrService.warning("Passwords do not match");
        }
        else {
            this.user.verifyPassword = undefined;
            this.userService.addUser(this.user).subscribe(function (res) {
                if (res) {
                    console.log(res);
                    _this.toastrService.success("You have successfully registed");
                    _this.router.navigate(["login"]);
                }
            }, function (err) {
                console.log(err);
                _this.toastrService.error("this account already has been registerd");
            });
        }
    };
    RegisterComponent = __decorate([
        core_1.Component({
            selector: 'app-register',
            templateUrl: './register.component.html',
            styleUrls: ['./register.component.css']
        })
    ], RegisterComponent);
    return RegisterComponent;
}());
exports.RegisterComponent = RegisterComponent;
