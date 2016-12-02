(function() {
    'use strict';

    angular
        .module('tennisBuddiesApp')
        .controller('Game_statusDialogController', Game_statusDialogController);

    Game_statusDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Game_status'];

    function Game_statusDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Game_status) {
        var vm = this;

        vm.game_status = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.game_status.id !== null) {
                Game_status.update(vm.game_status, onSaveSuccess, onSaveError);
            } else {
                Game_status.save(vm.game_status, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('tennisBuddiesApp:game_statusUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
