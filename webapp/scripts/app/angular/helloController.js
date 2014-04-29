/// <reference path="lib/angularjs/angular.d.ts"/>
var HelloApp;
(function (HelloApp) {
    var HelloController = (function () {
        function HelloController($scope) {
            this.$scope = $scope;
            this.setText();
        }
        HelloController.prototype.setText = function () {
            this.$scope.greeting = { text: 'Hello' };
        };
        return HelloController;
    })();
    HelloApp.HelloController = HelloController;

    // Define the Angular module for our application.
    angular.module("myApp", []).controller("HelloController", ["$scope", HelloApp.HelloController]);
})(HelloApp || (HelloApp = {}));
//# sourceMappingURL=helloController.js.map
