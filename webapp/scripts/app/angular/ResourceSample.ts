/// <reference path="../lib/angularjs/angular.d.ts"/>
/// <reference path="../lib/angularjs/angular-resource.d.ts"/>

module ResourceSampleApp 
{
	export class PersonResource
	{
		private resource:any;
		private getPersonAction : ng.resource.IActionDescriptor = {
            method: 'GET',
            isArray: true
        };
		
		constructor($resource : ng.resource.IResourceService)
		{
			this.resource = $resource( 'person.json',
							{},
							{getPerson: this.getPersonAction});
		}
	
		public getPersonList()
		{
			return this.resource.getPerson();
		}
	}
	
	export class ResourceController 
	{
		constructor(private $scope: ng.IScope, personResource:PersonResource) 
		{
	        this.$scope.persons = personResource.getPersonList();
	    }
	}
	
	
	// Define the Angular module for our application.
	angular.module("myApp", ['ngResource'])
		.service("PersonResource", ResourceSampleApp.PersonResource)
		.controller("ResourceController", ["$scope","PersonResource", ResourceSampleApp.ResourceController]);
	
}