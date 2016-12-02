(function() {
    'use strict';

    angular
        .module('tennisBuddiesApp')
        .controller('Game_statusController', Game_statusController);

    Game_statusController.$inject = ['$scope', '$state', 'Game_status'];

    function Game_statusController ($scope, $state, Game_status) {
        var vm = this;

        vm.game_statuses = [];

        loadAll();

        function loadAll() {
            Game_status.query(function(result) {
                vm.game_statuses = result;
            });
        }
    }
})();
