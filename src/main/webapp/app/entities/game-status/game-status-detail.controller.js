(function() {
    'use strict';

    angular
        .module('tennisBuddiesApp')
        .controller('Game_statusDetailController', Game_statusDetailController);

    Game_statusDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Game_status'];

    function Game_statusDetailController($scope, $rootScope, $stateParams, previousState, entity, Game_status) {
        var vm = this;

        vm.game_status = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('tennisBuddiesApp:game_statusUpdate', function(event, result) {
            vm.game_status = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
