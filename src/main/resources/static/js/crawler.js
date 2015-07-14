var app = angular.module('crawler', ['ngResource', 'ngRoute']);

var crawlerController = app.controller('crawlerCtrl', function ($scope, $resource, $http, $location) {
    $scope.goInto = function (f) {
        var isDir = !f || f.dir;
        var location = f && f.path;
        $http.get('/path' + (location || '/') + (isDir ? '.json' : '')).success(function (response) {
            $scope.ref = location;
            $scope.ls = response;
        }).error(function (data) {
            console.log('Stable? Who said stable? Take it:');
            console.dir(data);
        })
    };

    $scope.goUpper = function () {
        var lastSlashPos = $scope.ref.lastIndexOf('\/');
        $scope.goInto(lastSlashPos < 0 ? null : $scope.ref.substr(0, lastSlashPos));
    };


    $scope.goInto();
    $scope.ref = $location.path();
    console.dir($scope.ref);
});

crawlerController.factory('DirResource', function ($resource) {
    // Resource does not work. See encodeUriSegment function and related bug-reports. Slashes are always encoded wrong way.
    return $resource('/path:path');
});

app.config(['$routeProvider', function($routeProvider) {
    $routeProvider
        .when('/', { controller: 'crawlerCtrl', templateUrl: 'index.html' })
        .otherwise({ controller: 'crawlerCtrl', templateUrl: 'index.html'  });
}]);