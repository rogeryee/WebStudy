/// <reference path="../lib/angularjs/angular.d.ts"/>
var RouteSampleApp;
(function (RouteSampleApp) {
    // Define the Angular module for our application.
    var app = angular.module("RouteSampleApp", ['ngRoute']);

    // configure routes
    app.config(function ($routeProvider) {
        $routeProvider.when('/', {
            templateUrl: 'home.html',
            controller: 'mainController'
        }).when('/about', {
            templateUrl: 'about.html',
            controller: 'aboutController'
        }).when('/contact', {
            templateUrl: 'contact.html',
            controller: 'contactController'
        }).when('/help', {
            template: "<div class='jumbotron text-center'><h1>Help Page</h1><p>{{ message }}</p></div>",
            controller: 'helpController'
        });
    });

    // create the controller and inject Angular's $scope
    app.controller('mainController', function ($scope) {
        // create a message to display in our view
        $scope.message = 'Everyone come and see how good I look!';
    });

    app.controller('aboutController', function ($scope) {
        $scope.message = 'Look! I am an about page.';
    });

    app.controller('contactController', function ($scope) {
        $scope.message = 'Contact us!';
    });

    app.controller('helpController', function ($scope) {
        $scope.message = 'Welcome to Help';
    });
})(RouteSampleApp || (RouteSampleApp = {}));
//# sourceMappingURL=RouteSample.js.map
