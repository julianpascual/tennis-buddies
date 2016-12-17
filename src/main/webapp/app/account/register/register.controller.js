(function() {
    'use strict';

    angular
        .module('tennisBuddiesApp')
        .controller('RegisterController', RegisterController);


    RegisterController.$inject = [ '$timeout', 'Auth', 'LoginService'];

    function RegisterController ($timeout, Auth, LoginService) {
        var vm = this;

        vm.doNotMatch = null;
        vm.error = null;
        vm.errorUserExists = null;
        vm.login = LoginService.open;
        vm.register = register;
        vm.registerAccount = {};
        vm.success = null;
        vm.openCalendar = openCalendar;
        vm.datePickerOpenStatus = {};
        vm.format = 'dd-MMMM-yyyy';

        vm.dateOptions = {
            formatYear: 'yy',
            minDate: new Date(1900, 1, 1),
            startingDay: 1
        };

        $timeout(function (){angular.element('#login').focus();});

        function register () {
            if (vm.registerAccount.password !== vm.confirmPassword) {
                vm.doNotMatch = 'ERROR';
            } else {
                vm.registerAccount.langKey =  'en' ;
                vm.doNotMatch = null;
                vm.error = null;
                vm.errorUserExists = null;
                vm.errorEmailExists = null;

                Auth.createAccount(vm.registerAccount).then(function () {
                    vm.success = 'OK';
                }).catch(function (response) {
                    vm.success = null;
                    if (response.status === 400 && response.data === 'login already in use') {
                        vm.errorUserExists = 'ERROR';
                    } else if (response.status === 400 && response.data === 'e-mail address already in use') {
                        vm.errorEmailExists = 'ERROR';
                    } else {
                        vm.error = 'ERROR';
                    }
                });
            }
        }

        vm.datePickerOpenStatus.date = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;

        }
    }
})();
