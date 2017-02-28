(function() {
    'use strict';

    angular
        .module('tennisBuddiesApp')
        .controller('MessageController', MessageController);

    MessageController.$inject = ['$scope', '$state', 'Message'];

    function MessageController ($scope, $state, Message) {
        var vm = this;

        vm.messages = [];

        loadAll();

        function loadAll() {
            Message.query(function(result) {
                vm.messages = result;
            });

            console.log($state.params.idUserFrom);

            console.log($state.params.idUserTo);
        }
    }
})();
