(function() {
    'use strict';

    angular
        .module('tennisBuddiesApp')
        .controller('UserDetailController', UserDetailController);

    UserDetailController.$inject = ['$stateParams', 'User', 'Review'];

    function UserDetailController ($stateParams, User, Review) {
        var vm = this;

        vm.load = load;
        vm.user = {};
        vm.reviews = [];
        vm.rating = 0;

        vm.load($stateParams.login);



        function load (login) {
            User.get({login: login}, function(result) {
                vm.user = result;
            });
            Review.query({login: login}, function(result) {
                vm.reviews = result;
                calculateRating(vm.reviews);
            });
        }

        function calculateRating(reviews) {
            if (reviews.length > 0) {
                console.log(reviews);
                reviews.forEach( function (review)
                {
                    vm.rating += review.review;
                });
                vm.rating = vm.rating / reviews.length;
            }
        }

        vm.calculateAge = function calculateAge(birthday) {
            console.log('Se ejecuto');
            console.log(birthday);
            var ageDifMs = Date.now() - birthday.getTime();
            console.log(ageDifMs);
            var ageDate = new Date(ageDifMs);
            console.log(ageDate);
            return Math.abs(ageDate.getUTCFullYear() - 1970);
        }
    }
})();
