/// <reference path="../lib/angularjs/angular.d.ts"/>
/// <reference path="../lib/angularjs/angular-resource.d.ts"/>

module ResourceSampleApp 
{
	export class ResourceController 
	{
	    constructor(private $scope: ng.IScope, private webApi:WebApi) 
		{
	        this.$scope.persons = webApi.getPersonInfo("person");
	    }
	}
	
	export class WebApi
	{
		constructor(private $resource:ng.resource)
		{
			
		}
		
		public getPersonInfo(filename:string)
		{
			return this.$resource( 'person.json',{},{ getPerson: {method:'GET', params:{}, isArray:true}}).getPerson();
		}
	}
	
	// Define the Angular module for our application.
	angular.module("myApp", ['ngResource'])
		.service("WebApi", ResourceSampleApp.WebApi)
		.controller("ResourceController", ["$scope","WebApi", ResourceSampleApp.ResourceController]);
	
}