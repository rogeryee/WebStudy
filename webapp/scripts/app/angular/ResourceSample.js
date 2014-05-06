/// <reference path="../lib/angularjs/angular.d.ts"/>
/// <reference path="../lib/angularjs/angular-resource.d.ts"/>
var ResourceSampleApp;
(function (ResourceSampleApp) {
    var PersonResource = (function () {
        function PersonResource($resource) {
            this.getPersonAction = {
                method: 'GET',
                isArray: true
            };
            this.resource = $resource('person.json', {}, { getPerson: this.getPersonAction });
        }
        PersonResource.prototype.getPersonList = function () {
            return this.resource.getPerson();
        };
        return PersonResource;
    })();
    ResourceSampleApp.PersonResource = PersonResource;

    var ResourceController = (function () {
        function ResourceController($scope, personResource) {
            this.$scope = $scope;
            this.$scope.persons = personResource.getPersonList();
        }
        return ResourceController;
    })();
    ResourceSampleApp.ResourceController = ResourceController;

    // Define the Angular module for our application.
    angular.module("myApp", ['ngResource']).service("PersonResource", ResourceSampleApp.PersonResource).controller("ResourceController", ["$scope", "PersonResource", ResourceSampleApp.ResourceController]);
})(ResourceSampleApp || (ResourceSampleApp = {}));
//# sourceMappingURL=ResourceSample.js.map
