(function () {
    'use strict';

    angular
        .module('tennisBuddiesApp')
        .factory('UserReviews', UserReviews);

    UserReviews.$inject = ['$resource'];

    function UserReviews ($resource) {
        var service = $resource('api/reviews/user/:login', {}, {
            'query': {method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'save': { method:'POST' },
            'update': { method:'PUT' },
            'delete':{ method:'DELETE'}
        });

        return service;
    }
})();
