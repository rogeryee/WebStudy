/// <reference path="lib/angularjs/angular.d.ts"/>
var SimpleSampleApp;
(function (SimpleSampleApp) {
    var CartController = (function () {
        function CartController($scope) {
            var _this = this;
            this.$scope = $scope;
            this.$scope.items = [
                { title: 'Paint pots', quantity: 8, price: 3.95 },
                { title: 'Polka dots', quantity: 17, price: 12.95 },
                { title: 'Pebbles', quantity: 5, price: 6.95 }
            ];

            this.$scope.remove = function (index) {
                _this.$scope.items.splice(index, 1);
            };

            this.$scope.bill = {};

            this.$scope.$watch('items', this.calculateTotals, true);
        }
        CartController.prototype.calculateTotals = function (newValue, oldValue, scope) {
            var total = 0;
            for (var i = 0, len = scope.items.length; i < len; i++) {
                total = total + scope.items[i].price * scope.items[i].quantity;
            }

            scope.bill.total = total;
            scope.bill.discount = total > 100 ? 10 : 0;
            scope.bill.subtotal = total - scope.bill.discount;
        };
        return CartController;
    })();
    SimpleSampleApp.CartController = CartController;

    var StartUpController = (function () {
        function StartUpController($scope) {
            var _this = this;
            this.$scope = $scope;
            this.$scope.funding = { startingEstimate: 0 };
            this.$scope.computeNeeded = function () {
                $scope.funding.needed = $scope.funding.startingEstimate * 10;
            };

            this.$scope.requestFunding = function () {
                alert("Sorry, please get more customers first.");
            };

            this.$scope.reset = function () {
                _this.$scope.funding.startingEstimate = 0;
            };

            // If the value of "funding.startingEstimate" is changed, it will trigger the method 'computerNeeded'
            this.$scope.$watch('funding.startingEstimate', this.$scope.computeNeeded);
        }
        return StartUpController;
    })();
    SimpleSampleApp.StartUpController = StartUpController;

    var StudentListController = (function () {
        function StudentListController($scope) {
            var _this = this;
            this.$scope = $scope;
            var students = [
                { name: 'Mary Contrary', id: '1' },
                { name: 'Jack Sprat', id: '2' },
                { name: 'Jill Hill', id: '3' }];

            this.$scope.students = students;
            this.$scope.insertTom = function () {
                _this.$scope.students.splice(1, 0, { name: 'Tom Thumb', id: '4' });
            };

            this.$scope.menuState = { show: false };
            this.$scope.toggleMenu = function () {
                _this.$scope.menuState.show = !_this.$scope.menuState.show;
            };

            this.$scope.isDisabled = false;
            this.$scope.Disable = { text: 'Disable' };
            this.$scope.disableMenu = function () {
                _this.$scope.isDisabled = !_this.$scope.isDisabled;
                if (_this.$scope.isDisabled) {
                    _this.$scope.Disable = { text: 'Enable' };
                } else {
                    _this.$scope.Disable = { text: 'Disable' };
                }
            };
        }
        return StudentListController;
    })();
    SimpleSampleApp.StudentListController = StudentListController;

    var CssController = (function () {
        function CssController($scope) {
            var _this = this;
            this.$scope = $scope;
            this.$scope.isError = false;
            this.$scope.isWarning = false;
            this.$scope.showError = function () {
                _this.$scope.messageText = 'This is an error!';
                _this.$scope.isError = true;
                _this.$scope.isWarning = false;
            };

            this.$scope.showWarning = function () {
                _this.$scope.messageText = 'Just a warning. Please carry on.';
                _this.$scope.isWarning = true;
                _this.$scope.isError = false;
            };
        }
        return CssController;
    })();
    SimpleSampleApp.CssController = CssController;

    var RestaurantTableController = (function () {
        function RestaurantTableController($scope) {
            var _this = this;
            this.$scope = $scope;
            this.$scope.directory = [
                { name: 'The Handsome Heifer', cuisine: 'BBQ' },
                { name: "Green's Green Greens", cuisine: 'Salads' },
                { name: 'House of Fine Fish', cuisine: 'Seafood' }];
            this.$scope.selectRestaurant = function (row) {
                _this.$scope.selectedRow = row;
            };
        }
        return RestaurantTableController;
    })();
    SimpleSampleApp.RestaurantTableController = RestaurantTableController;

    var FilterController = (function () {
        function FilterController($scope) {
            this.$scope = $scope;
            this.$scope.pageHeading = 'behold the majesty of your page title';
        }
        return FilterController;
    })();
    SimpleSampleApp.FilterController = FilterController;

    // Define the Angular module for our application.
    angular.module("SimpleSampleApp", []).controller("CartController", ["$scope", SimpleSampleApp.CartController]).controller("StartUpController", ["$scope", SimpleSampleApp.StartUpController]).controller("StudentListController", ["$scope", SimpleSampleApp.StudentListController]).controller("CssController", ["$scope", SimpleSampleApp.CssController]).controller("RestaurantTableController", ["$scope", SimpleSampleApp.RestaurantTableController]).controller("FilterController", ["$scope", SimpleSampleApp.FilterController]).filter('titleCase', function () {
        var titleCaseFilter = function (input) {
            var words = input.split(' ');
            for (var i = 0; i < words.length; i++) {
                words[i] = words[i].charAt(0).toUpperCase() + words[i].slice(1);
            }
            return words.join(' ');
        };
        return titleCaseFilter;
    });
})(SimpleSampleApp || (SimpleSampleApp = {}));
//# sourceMappingURL=SimpleSample.js.map
