/// <reference path="../lib/angularjs/angular.d.ts"/>

module SimpleXHRApp 
{
	export class XHRController 
	{
	    constructor(private $scope: ng.IScope, private $http) 
		{
	        $http.get('person.json').success(function(data) {
			    $scope.persons = data;
			  });
	    }
	}
	
	// Define the Angular module for our application.
	angular.module("myApp", []).controller("XHRController", ["$scope","$http", SimpleXHRApp.XHRController]);
	
}