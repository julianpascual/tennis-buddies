(function() {
    'use strict';

    angular
        .module('tennisBuddiesApp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope', 'Principal', 'LoginService', 'User', 'NgMap' , '$state'];

    function HomeController ($scope, Principal, LoginService, User, NgMap, $state) {
        var vm = this;
        vm.account = null;
        vm.users = [];
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        vm.review = 3;
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        NgMap.getMap().then(function(map) {
            vm.map = map;
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
            var hiddenUsersSize = 0;
            for (var i in data) {
                if (vm.account != null) {
                    if(data[i]['login'] == vm.account.login) {
                        data.splice(i, 1);
                        hiddenUsersSize++;
                    }
                }
            }
            vm.users = data;
        }

        function onError(error) {
            console.log('Error retrieving users: ', error);
        }


        vm.showDetail = function(e, user) {
            vm.user = user;
            vm.map.showInfoWindow('user-info', 'm' + user.id);
        };

        vm.hideDetail = function() {
            vm.map.hideInfoWindow('user-info');
        };
    }
})();
