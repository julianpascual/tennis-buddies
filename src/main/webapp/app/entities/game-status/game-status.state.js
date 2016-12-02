(function() {
    'use strict';

    angular
        .module('tennisBuddiesApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('game-status', {
            parent: 'entity',
            url: '/game-status',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Game_statuses'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/game-status/game-statuses.html',
                    controller: 'Game_statusController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('game-status-detail', {
            parent: 'entity',
            url: '/game-status/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Game_status'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/game-status/game-status-detail.html',
                    controller: 'Game_statusDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Game_status', function($stateParams, Game_status) {
                    return Game_status.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'game-status',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('game-status-detail.edit', {
            parent: 'game-status-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/game-status/game-status-dialog.html',
                    controller: 'Game_statusDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Game_status', function(Game_status) {
                            return Game_status.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('game-status.new', {
            parent: 'game-status',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/game-status/game-status-dialog.html',
                    controller: 'Game_statusDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                status: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('game-status', null, { reload: 'game-status' });
                }, function() {
                    $state.go('game-status');
                });
            }]
        })
        .state('game-status.edit', {
            parent: 'game-status',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/game-status/game-status-dialog.html',
                    controller: 'Game_statusDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Game_status', function(Game_status) {
                            return Game_status.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('game-status', null, { reload: 'game-status' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('game-status.delete', {
            parent: 'game-status',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/game-status/game-status-delete-dialog.html',
                    controller: 'Game_statusDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Game_status', function(Game_status) {
                            return Game_status.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('game-status', null, { reload: 'game-status' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
