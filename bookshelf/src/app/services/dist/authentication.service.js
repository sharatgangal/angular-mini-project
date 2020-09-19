"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
exports.AuthenticationService = void 0;
var core_1 = require("@angular/core");
var AuthenticationService = /** @class */ (function () {
    /**
     *
     * @param httpClient :to communicate with backend services
     */
    function AuthenticationService(httpClient) {
        this.httpClient = httpClient;
        this.isUserlogedIn = false;
        this.authUrl = 'http://localhost:9999/api/v1/user/login';
    }
    //to set the 
    AuthenticationService.prototype.setIsUserLogedin = function (islogedIn) {
        this.isUserlogedIn = islogedIn;
    };
    AuthenticationService.prototype.getIsUserLogedIn = function () {
        return this.isUserlogedIn;
    };
    AuthenticationService.prototype.isUserLogedin = function () {
        if (localStorage.getItem('user')) {
            return true;
        }
        else {
            return false;
        }
    };
    AuthenticationService.prototype.setBearerToken = function (token) {
        localStorage.setItem('bearerToken', token);
    };
    AuthenticationService.prototype.getBearerToken = function () {
        return localStorage.getItem('bearerToken');
    };
    AuthenticationService.prototype.setCurrentUser = function (user) {
        localStorage.setItem('user', user);
    };
    AuthenticationService.prototype.getCurrentUser = function () {
        return localStorage.getItem('user');
    };
    AuthenticationService.prototype.validateUser = function (emailId, password) {
        return this.httpClient.get(this.authUrl + "/" + emailId + "," + password);
    };
    AuthenticationService = __decorate([
        core_1.Injectable({
            providedIn: 'root'
        })
    ], AuthenticationService);
    return AuthenticationService;
}());
exports.AuthenticationService = AuthenticationService;
