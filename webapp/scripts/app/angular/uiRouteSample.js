/// <reference path="lib/angularjs/angular.d.ts"/>
/// <reference path="lib/angularjs/angular.d.ts" />
var uiRouterApp;
(function (uiRouterApp) {
    var RouteController = (function () {
        function RouteController($scope) {
            this.$scope = $scope;
            this.$scope.items = ["A", "List", "Of", "Items"];
            this.$scope.things = ["A", "Set", "Of", "Things"];
        }
        return RouteController;
    })();
    uiRouterApp.RouteController = RouteController;

    // Define the Angular module for our application.
    var app = angular.module("myApp", ["ui.router"]);
    app.controller('uiRouterController', uiRouterApp.RouteController);
    app.config(function ($stateProvider, $urlRouterProvider) {
        // For any unmatched url, send to /route1
        $urlRouterProvider.otherwise("/route1");

        $stateProvider.state('route1', {
            url: "/route1",
            template: "<h1>Route 1</h1>" + "<hr/>" + "<a ui-sref='.list'>Show List</a>" + "<div ui-view></div>"
        }).state('route1.list', {
            url: "/list",
            template: "<h3>List of Route 1 Items</h3>" + "<ul>" + "  <li ng-repeat='item in items'>{{item}}</li>" + "</ul>",
            controller: 'uiRouterController'
        }).state('route2', {
            url: "/route2",
            template: "<h1>Route 2</h1>" + "<hr/>" + "<a ui-sref='.list'>Show Set</a>" + "<div ui-view></div>"
        }).state('route2.list', {
            url: "/list",
            templateProvider: function () {
                return "<h3>Set of Route 2 Items</h3>" + "<ul>" + "  <li ng-repeat='item in items'>{{item}}</li>" + "</ul>";
            },
            controller: 'uiRouterController'
        });
    });
})(uiRouterApp || (uiRouterApp = {}));
//# sourceMappingURL=uiRouteSample.js.map
