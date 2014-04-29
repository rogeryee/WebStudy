/// <reference path="../lib/angularjs/angular.d.ts"/>

module ServiceSampleApp 
{
	export class CalculatorController 
	{	
		constructor(private $scope: ng.IScope, private calculatorService:CalculatorService)
		{
			this.$scope.doSquare = () => {
				this.$scope.answer = this.calculatorService.square(this.$scope.number);
			}
			
			this.$scope.doCube = () => {
				this.$scope.answer = this.calculatorService.cube(this.$scope.number);
			}
		}
	}
	
	export class MathService
	{
		public add(a:number,b:number):number
		{
			return a + b;
		}
		
		public subtract(a:number,b:number):number
		{
			return a - b;
		}
		
		public multiply(a:number,b:number):number
		{
			return a * b;
		}
		
		public divide(a:number,b:number):number
		{
			return a / b;
		}
	}
	
	export class CalculatorService
	{
		constructor(private mathService:MathService)
		{
			
		}
		
		public square(a:number):number
		{
			return this.mathService.multiply(a,a);
		}
		
		public cube(a:number):number
		{
			return this.mathService.multiply(a, this.mathService.multiply(a,a));
		}
	}
	
	export class ContactService
	{
		private uid:number;
		private contacts:any[];
		
		constructor()
		{
			this.init();
		}
		
		private init()
		{
			this.uid = 1;
			this.contacts = [{
		        id: 0,
		        'name': 'Viral',
		        'email': 'hello@gmail.com',
		        'phone': '123-2343-44'
		    }];
		}
		
		public save(contact:any):void
		{
			if (contact.id == null) 
			{
	            //if this is new contact, add it in contacts array
	            contact.id = this.uid++;
	            this.contacts.push(contact);
	        } 
			else 
			{
	            //for existing contact, find this contact using id and update it.
				angular.forEach(this.contacts, (c:any) => {
					if (c.id == contact.id) 
						c = contact;
				});
	        }
		}
		
		public get(id:number):any
		{
			var contact;
			angular.forEach(this.contacts,(c:any) => {
				if (c.id == id) 
					contact = c;
			});
			return contact;
		}
		
		public list():any
		{
			return this.contacts;
		}
		
		public delete(id):void
		{
			var i = 0;		
			angular.forEach(this.contacts,(contact:any) => {
				if (contact.id == id) 
					this.contacts.splice(i, 1);
				i++;
			});
		}
	}
 
	export class ContactController
	{
		constructor(private $scope: ng.IScope, private contactService:ContactService)
		{
			this.init();
		}
		
		private init()
		{
			this.$scope.contacts = this.contactService.list();
			
			this.$scope.saveContact = () => {
        		this.contactService.save(this.$scope.newcontact);
        		this.$scope.newcontact = {};
    		}
  
    		this.$scope.delete = (id) => {
		        this.contactService.delete(id);
		        if (this.$scope.newcontact.id == id) 
					this.$scope.newcontact = {};
		    }
 
 
    		this.$scope.edit = (id) => {
				var old = this.contactService.get(id);
        		var cloned = angular.copy(old);
				this.$scope.newcontact = cloned;
    		}
		}
	}
	
	// Define the Angular module for our application.
	var app = angular.module("serviceApp", []);
	
	app.service('MathService', ServiceSampleApp.MathService);
	app.service('CalculatorService', ['MathService',ServiceSampleApp.CalculatorService]);
	app.controller("CalculatorController", ["$scope", 'CalculatorService', ServiceSampleApp.CalculatorController]);
	
	app.service('ContactService', ServiceSampleApp.ContactService);
	app.controller("ContactController", ["$scope", 'ContactService', ServiceSampleApp.ContactController]);
}
