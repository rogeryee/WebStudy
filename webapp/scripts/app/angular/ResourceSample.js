/// <reference path="../lib/angularjs/angular.d.ts"/>
/// <reference path="../lib/angularjs/angular-resource.d.ts"/>
var ResourceSampleApp;
(function (ResourceSampleApp) {
    var ResourceController = (function () {
        function ResourceController($scope, webApi) {
            this.$scope = $scope;
            this.webApi = webApi;
            this.$scope.persons = webApi.getPersonInfo("person");
        }
        return ResourceController;
    })();
    ResourceSampleApp.ResourceController = ResourceController;

    var WebApi = (function () {
        function WebApi($resource) {
            this.$resource = $resource;
        }
        WebApi.prototype.getPersonInfo = function (filename) {
            return this.$resource('person.json', {}, { getPerson: { method: 'GET', params: {}, isArray: true } }).getPerson();
        };
        return WebApi;
    })();
    ResourceSampleApp.WebApi = WebApi;

    // Define the Angular module for our application.
    angular.module("myApp", ['ngResource']).service("WebApi", ResourceSampleApp.WebApi).controller("ResourceController", ["$scope", "WebApi", ResourceSampleApp.ResourceController]);
})(ResourceSampleApp || (ResourceSampleApp = {}));
//# sourceMappingURL=ResourceSample.js.map
