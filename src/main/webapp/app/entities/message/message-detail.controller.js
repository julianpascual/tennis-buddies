(function() {
    'use strict';

    angular
        .module('tennisBuddiesApp')
        .controller('MessageDetailController', MessageDetailController);

    MessageDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Message', 'User'];

    function MessageDetailController($scope, $rootScope, $stateParams, previousState, entity, Message, User) {
        var vm = this;

        vm.message = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('tennisBuddiesApp:messageUpdate', function(event, result) {
            vm.message = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
