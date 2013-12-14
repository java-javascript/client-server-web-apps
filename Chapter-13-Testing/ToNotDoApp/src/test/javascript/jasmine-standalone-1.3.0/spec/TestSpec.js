'use strict';

describe("MyApp Validation", function () {

    it("expects moment.min.js to be available and functional", function () {
        expect(moment("20111031", "YYYYMMDD").format('MMMM Do YYYY, h:mm:ss a')).toBe("October 31st 2011, 12:00:00 am");
    });

});

/*
 This will pass in modern browsers, but fails in the maven plugin

 describe('AppCtrl', function () {

 beforeEach(module('MyApp'));

 var ctrl, scope;

 beforeEach(inject(function ($controller, $rootScope) {
 scope = $rootScope.$new();
 ctrl = $controller('AppCtrl', {
 $scope: scope
 });
 }));

 it('Title to be set in the controller', function () {
 expect(scope.title).toBe("To NOT Do");
 });
 });

 */


