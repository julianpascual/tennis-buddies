(function() {
    'use strict';

    angular
        .module('tennisBuddiesApp')
        .controller('Game_statusDeleteController',Game_statusDeleteController);

    Game_statusDeleteController.$inject = ['$uibModalInstance', 'entity', 'Game_status'];

    function Game_statusDeleteController($uibModalInstance, entity, Game_status) {
        var vm = this;

        vm.game_status = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Game_status.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
