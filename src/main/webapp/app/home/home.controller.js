(function() {
    'use strict';

    angular
        .module('tennisBuddiesApp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope', 'Principal', 'LoginService', 'NgMap', '$state'];

    function HomeController ($scope, Principal, LoginService, NgMap, $state) {
        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        NgMap.getMap().then(function(map) {
            console.log(map.getCenter());
            console.log('markers', map.markers);
            console.log('shapes', map.shapes);
        });

        getAccount();

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }
        function register () {
            $state.go('register');
        }

        function getMap() {
            ngMap.getMap().then(function (map) {
                console.log(map.getCenter());
                console.log('markers', map.getMarkers());
            })
        }
    }
})();
