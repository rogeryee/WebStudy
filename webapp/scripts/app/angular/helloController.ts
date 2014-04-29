/// <reference path="../lib/angularjs/angular.d.ts"/>

module HelloApp 
{
	export class HelloController 
	{
	    constructor(private $scope: ng.IScope) 
		{
	        this.setText();
	    }
		
		setText()
		{
			this.$scope.greeting = {text : 'Hello'};
		}
	}
	
	// Define the Angular module for our application.
	angular.module("myApp", []).controller("HelloController", ["$scope", HelloApp.HelloController]);
	
}