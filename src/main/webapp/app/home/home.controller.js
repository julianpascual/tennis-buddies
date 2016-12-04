(function() {
    'use strict';

    angular
        .module('tennisBuddiesApp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope', 'Principal', 'LoginService', 'NgMap', 'User' , '$state'];

    function HomeController ($scope, Principal, LoginService, NgMap, User, $state) {
        var vm = this;
        vm.account = null;
        vm.users = [];
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        NgMap.getMap().then(function(map) {

        });

        getAccount();

        getAllUsers();

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }
        function register () {
            $state.go('register');
        }

        function getAllUsers () {
            User.query(null, onSuccess, onError);
        }

        function onSuccess(data, headers) {
            //hide anonymous user from user management: it's a required user for Spring Security
            var hiddenUsersSize = 0;
            for (var i in data) {
                if (data[i]['login'] === 'anonymoususer') {
                    data.splice(i, 1);
                    hiddenUsersSize++;
                }
            }
            vm.users = data;
        }

        function onError(error) {
            console.log('Error retrieving users: ', error);
        }

        $scope.mySplit = function(string, nb) {
            var array = string.split(';');
            return array[nb];
        }
    }
})();
