(function() {
    'use strict';
    angular
        .module('tennisBuddiesApp')
        .factory('Game_status', Game_status);

    Game_status.$inject = ['$resource'];

    function Game_status ($resource) {
        var resourceUrl =  'api/game-statuses/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
