(function() {
    'use strict';

    angular
        .module('tennisBuddiesApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('home', {
            parent: 'app',
            url: '/',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/home/home.html',
                    controller: 'HomeController',
                    controllerAs: 'vm'
                }
            }
        })
        .state('user-detail', {
            parent: 'app',
            url: '/user-details/:login',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/user-details/user-detail.html',
                    controller: 'UserDetailController',
                    controllerAs: 'vm'
                }
            }
        });
    }
})();
