"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
exports.HeaderComponent = void 0;
var core_1 = require("@angular/core");
var HeaderComponent = /** @class */ (function () {
    /**
     *
     * @param authentication :to get the user status
     * @param toastr :to display message to user
     */
    function HeaderComponent(authentication, toastr) {
        this.authentication = authentication;
        this.toastr = toastr;
    }
    HeaderComponent.prototype.ngOnInit = function () {
    };
    /**
     * to check if the user logged in or not
     */
    HeaderComponent.prototype.isUserlogedIn = function () {
        return this.authentication.isUserLogedin();
    };
    /**
     * if the user clicks on logout then we have to clear the localstorage
     */
    HeaderComponent.prototype.onLogout = function () {
        localStorage.clear();
        console.log("logout");
        this.toastr.success("logout successfully");
        // this.isLogedIn=false;
    };
    HeaderComponent = __decorate([
        core_1.Component({
            selector: 'app-header',
            templateUrl: './header.component.html',
            styleUrls: ['./header.component.css']
        })
    ], HeaderComponent);
    return HeaderComponent;
}());
exports.HeaderComponent = HeaderComponent;
