angular.module('ngApp', [])
    .controller('userCon', function($scope, $http) {
        $http.get('http://localhost:8008/users').
        then(function(response) {
            $scope.users = response.data;
        });
    });