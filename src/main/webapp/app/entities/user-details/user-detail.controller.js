(function() {
    'use strict';

    angular
        .module('tennisBuddiesApp')
        .controller('UserDetailController', UserDetailController);

    UserDetailController.$inject = ['$stateParams', 'User'];

    function UserDetailController ($stateParams, User) {
        var vm = this;

        vm.load = load;
        vm.user = {};

        vm.load($stateParams.login);

        function load (login) {
            User.get({login: login}, function(result) {
                vm.user = result;
            });
        }
    }
})();
