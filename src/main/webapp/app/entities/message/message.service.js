(function() {
    'use strict';
    angular
        .module('tennisBuddiesApp')
        .factory('Message', Message);

    Message.$inject = ['$resource', 'DateUtils'];

    function Message ($resource, DateUtils) {
        var resourceUrl =  'api/messages/:id';

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
