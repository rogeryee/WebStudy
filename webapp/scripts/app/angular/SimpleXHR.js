/// <reference path="../lib/angularjs/angular.d.ts"/>
var SimpleXHRApp;
(function (SimpleXHRApp) {
    var XHRController = (function () {
        function XHRController($scope, $http) {
            this.$scope = $scope;
            this.$http = $http;
            $http.get('person.json').success(function (data) {
                $scope.persons = data;
            });
        }
        return XHRController;
    })();
    SimpleXHRApp.XHRController = XHRController;

    // Define the Angular module for our application.
    angular.module("myApp", []).controller("XHRController", ["$scope", "$http", SimpleXHRApp.XHRController]);
})(SimpleXHRApp || (SimpleXHRApp = {}));
//# sourceMappingURL=SimpleXHR.js.map
