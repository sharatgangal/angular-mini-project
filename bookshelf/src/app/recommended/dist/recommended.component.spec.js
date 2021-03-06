"use strict";
exports.__esModule = true;
var testing_1 = require("@angular/core/testing");
var recommended_component_1 = require("./recommended.component");
describe('RecommendedComponent', function () {
    var component;
    var fixture;
    beforeEach(testing_1.async(function () {
        testing_1.TestBed.configureTestingModule({
            declarations: [recommended_component_1.RecommendedComponent],
            imports: []
        })
            .compileComponents();
    }));
    beforeEach(function () {
        fixture = testing_1.TestBed.createComponent(recommended_component_1.RecommendedComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });
    it('should create', function () {
        expect(component).toBeTruthy();
    });
});
