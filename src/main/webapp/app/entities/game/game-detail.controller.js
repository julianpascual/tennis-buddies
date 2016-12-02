(function() {
    'use strict';

    angular
        .module('tennisBuddiesApp')
        .controller('GameDetailController', GameDetailController);

    GameDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Game', 'Game_status', 'User'];

    function GameDetailController($scope, $rootScope, $stateParams, previousState, entity, Game, Game_status, User) {
        var vm = this;

        vm.game = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('tennisBuddiesApp:gameUpdate', function(event, result) {
            vm.game = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
