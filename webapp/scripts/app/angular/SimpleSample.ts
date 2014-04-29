/// <reference path="../lib/angularjs/angular.d.ts"/>

module SimpleSampleApp 
{
	export class CartController 
	{
	    constructor(private $scope: ng.IScope) 
		{
	        this.$scope.items = [
				{title: 'Paint pots', quantity: 8, price: 3.95},
				{title: 'Polka dots', quantity: 17, price: 12.95},
				{title: 'Pebbles', quantity: 5, price: 6.95}
				];
			
			this.$scope.remove = (index) => {
				this.$scope.items.splice(index, 1);
			}
			
			this.$scope.bill = {};
			
			this.$scope.$watch('items', this.calculateTotals, true);
	    }
		
		calculateTotals(newValue,oldValue,scope) 
		{
			var total = 0;
			for (var i = 0, len = scope.items.length; i < len; i++) 
			{
				total = total + scope.items[i].price * scope.items[i].quantity;
			}
			
			scope.bill.total = total;
			scope.bill.discount = total > 100 ? 10 : 0;
			scope.bill.subtotal = total - scope.bill.discount;
		}
	}
	
	export class StartUpController
	{
		constructor(private $scope: ng.IScope) 
		{
			this.$scope.funding = { startingEstimate: 0 };
			this.$scope.computeNeeded = () => {
				$scope.funding.needed = $scope.funding.startingEstimate * 10;
			};
			
			this.$scope.requestFunding = () => {
				alert("Sorry, please get more customers first.");
			};
			
			this.$scope.reset = () => {
				this.$scope.funding.startingEstimate = 0;
			};
			
			// If the value of "funding.startingEstimate" is changed, it will trigger the method 'computerNeeded'
			this.$scope.$watch('funding.startingEstimate', this.$scope.computeNeeded);
		}		
	}
	
	export class StudentListController
	{
		constructor(private $scope: ng.IScope) 
		{
			var students = [{name:'Mary Contrary', id:'1'},
							{name:'Jack Sprat', id:'2'},
							{name:'Jill Hill', id:'3'}];
			
			this.$scope.students = students;
			this.$scope.insertTom = () => {
				this.$scope.students.splice(1, 0, {name:'Tom Thumb', id:'4'});
			};
			
			this.$scope.menuState = { show:false};
			this.$scope.toggleMenu = () => {
				this.$scope.menuState.show = !this.$scope.menuState.show;
			};
			
			this.$scope.isDisabled = false;
			this.$scope.Disable = {text:'Disable'};
			this.$scope.disableMenu = () => {
				this.$scope.isDisabled = !this.$scope.isDisabled;
				if(this.$scope.isDisabled)
				{
					this.$scope.Disable = {text:'Enable'};
				}
				else
				{
					this.$scope.Disable = {text:'Disable'};
				}
			};
		}
	}
	
	export class CssController
	{
		constructor(private $scope: ng.IScope) 
		{
			this.$scope.isError = false;
			this.$scope.isWarning = false;
			this.$scope.showError = ()=> {
				this.$scope.messageText = 'This is an error!';
				this.$scope.isError = true;
				this.$scope.isWarning = false;
			};
			
			this.$scope.showWarning = () => {
				this.$scope.messageText = 'Just a warning. Please carry on.';
				this.$scope.isWarning = true;
				this.$scope.isError = false;
			};
		}
	}
	
	export class RestaurantTableController
	{
		constructor(private $scope: ng.IScope) 
		{
			this.$scope.directory = [{name:'The Handsome Heifer', cuisine:'BBQ'},
									{name:"Green's Green Greens", cuisine:'Salads'},
									{name:'House of Fine Fish', cuisine:'Seafood'}];
			this.$scope.selectRestaurant = (row) => {
				this.$scope.selectedRow = row;
			};
		}
	}
	
	export class FilterController
	{
		constructor(private $scope: ng.IScope) 
		{
			this.$scope.pageHeading = 'behold the majesty of your page title';
		}
	}
	
	// Define the Angular module for our application.
	angular.module("SimpleSampleApp", [])
		.controller("CartController", ["$scope", SimpleSampleApp.CartController])
		.controller("StartUpController", ["$scope", SimpleSampleApp.StartUpController])
		.controller("StudentListController", ["$scope", SimpleSampleApp.StudentListController])
		.controller("CssController", ["$scope", SimpleSampleApp.CssController])
		.controller("RestaurantTableController", ["$scope", SimpleSampleApp.RestaurantTableController])
		.controller("FilterController", ["$scope", SimpleSampleApp.FilterController])
		.filter('titleCase', () => {
			var titleCaseFilter = (input) => {
					var words = input.split(' ');
					for (var i = 0; i < words.length; i++) 
							{
						words[i] = words[i].charAt(0).toUpperCase() + words[i].slice(1);
					}
					return words.join(' ');
				};
			return titleCaseFilter;
		});
}
