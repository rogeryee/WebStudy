/// <reference path="../lib/angularjs/angular.d.ts"/>
var ServiceSampleApp;
(function (ServiceSampleApp) {
    var CalculatorController = (function () {
        function CalculatorController($scope, calculatorService) {
            var _this = this;
            this.$scope = $scope;
            this.calculatorService = calculatorService;
            this.$scope.doSquare = function () {
                _this.$scope.answer = _this.calculatorService.square(_this.$scope.number);
            };

            this.$scope.doCube = function () {
                _this.$scope.answer = _this.calculatorService.cube(_this.$scope.number);
            };
        }
        return CalculatorController;
    })();
    ServiceSampleApp.CalculatorController = CalculatorController;

    var MathService = (function () {
        function MathService() {
        }
        MathService.prototype.add = function (a, b) {
            return a + b;
        };

        MathService.prototype.subtract = function (a, b) {
            return a - b;
        };

        MathService.prototype.multiply = function (a, b) {
            return a * b;
        };

        MathService.prototype.divide = function (a, b) {
            return a / b;
        };
        return MathService;
    })();
    ServiceSampleApp.MathService = MathService;

    var CalculatorService = (function () {
        function CalculatorService(mathService) {
            this.mathService = mathService;
        }
        CalculatorService.prototype.square = function (a) {
            return this.mathService.multiply(a, a);
        };

        CalculatorService.prototype.cube = function (a) {
            return this.mathService.multiply(a, this.mathService.multiply(a, a));
        };
        return CalculatorService;
    })();
    ServiceSampleApp.CalculatorService = CalculatorService;

    var ContactService = (function () {
        function ContactService() {
            this.init();
        }
        ContactService.prototype.init = function () {
            this.uid = 1;
            this.contacts = [{
                    id: 0,
                    'name': 'Viral',
                    'email': 'hello@gmail.com',
                    'phone': '123-2343-44'
                }];
        };

        ContactService.prototype.save = function (contact) {
            if (contact.id == null) {
                //if this is new contact, add it in contacts array
                contact.id = this.uid++;
                this.contacts.push(contact);
            } else {
                //for existing contact, find this contact using id and update it.
                angular.forEach(this.contacts, function (c) {
                    if (c.id == contact.id)
                        c = contact;
                });
            }
        };

        ContactService.prototype.get = function (id) {
            var contact;
            angular.forEach(this.contacts, function (c) {
                if (c.id == id)
                    contact = c;
            });
            return contact;
        };

        ContactService.prototype.list = function () {
            return this.contacts;
        };

        ContactService.prototype.delete = function (id) {
            var _this = this;
            var i = 0;
            angular.forEach(this.contacts, function (contact) {
                if (contact.id == id)
                    _this.contacts.splice(i, 1);
                i++;
            });
        };
        return ContactService;
    })();
    ServiceSampleApp.ContactService = ContactService;

    var ContactController = (function () {
        function ContactController($scope, contactService) {
            this.$scope = $scope;
            this.contactService = contactService;
            this.init();
        }
        ContactController.prototype.init = function () {
            var _this = this;
            this.$scope.contacts = this.contactService.list();

            this.$scope.saveContact = function () {
                _this.contactService.save(_this.$scope.newcontact);
                _this.$scope.newcontact = {};
            };

            this.$scope.delete = function (id) {
                _this.contactService.delete(id);
                if (_this.$scope.newcontact.id == id)
                    _this.$scope.newcontact = {};
            };

            this.$scope.edit = function (id) {
                var old = _this.contactService.get(id);
                var cloned = angular.copy(old);
                _this.$scope.newcontact = cloned;
            };
        };
        return ContactController;
    })();
    ServiceSampleApp.ContactController = ContactController;

    // Define the Angular module for our application.
    var app = angular.module("serviceApp", []);

    app.service('MathService', ServiceSampleApp.MathService);
    app.service('CalculatorService', ['MathService', ServiceSampleApp.CalculatorService]);
    app.controller("CalculatorController", ["$scope", 'CalculatorService', ServiceSampleApp.CalculatorController]);

    app.service('ContactService', ServiceSampleApp.ContactService);
    app.controller("ContactController", ["$scope", 'ContactService', ServiceSampleApp.ContactController]);
})(ServiceSampleApp || (ServiceSampleApp = {}));
//# sourceMappingURL=ServiceSample.js.map
