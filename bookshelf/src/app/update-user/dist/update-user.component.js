"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
exports.UpdateUserComponent = void 0;
var core_1 = require("@angular/core");
var user_1 = require("../model/user");
var UpdateUserComponent = /** @class */ (function () {
    /**
     *
     * @param userService userService:to update user:
     * @param authenticationService :to get the current user
     * @param toastr :to display message to user
     */
    function UpdateUserComponent(userService, authenticationService, toastr) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.toastr = toastr;
        /**
         * parameters:user:to access the properties of user.
         * genders:for user gender selection.
         * countries:for user country selection.
         *
         */
        this.user = new user_1.User();
        this.genders = ["Male", "Female", "Others"];
        this.countries = ["India", "USA", "UK", "Germany"];
    }
    /**
     * getting the current user details
     */
    UpdateUserComponent.prototype.ngOnInit = function () {
        var currentUser = this.authenticationService.getCurrentUser();
        this.user.userId = currentUser;
    };
    /**
     * update the user with the help of userService by passing the new user details.
     */
    UpdateUserComponent.prototype.updateUser = function () {
        var _this = this;
        console.log(this.user);
        this.userService.userToUpdate(this.user).subscribe(function (res) {
            if (res) {
                console.log(res);
                console.log("Updated Successfully");
                _this.toastr.success("updated successfully");
            }
            else {
                _this.toastr.error("invalid user credentials");
            }
        }, function (err) {
            console.log(err);
            _this.toastr.error("invalid user credentials");
        });
    };
    UpdateUserComponent = __decorate([
        core_1.Component({
            selector: 'app-update-user',
            templateUrl: './update-user.component.html',
            styleUrls: ['./update-user.component.css']
        })
    ], UpdateUserComponent);
    return UpdateUserComponent;
}());
exports.UpdateUserComponent = UpdateUserComponent;
