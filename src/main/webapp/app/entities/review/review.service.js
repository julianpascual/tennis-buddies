(function() {
    'use strict';
    angular
        .module('tennisBuddiesApp')
        .factory('Review', Review);

    Review.$inject = ['$resource', 'DateUtils'];

    function Review ($resource, DateUtils) {
        var resourceUrl =  'api/reviews/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.date = DateUtils.convertDateTimeFromServer(data.date);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
