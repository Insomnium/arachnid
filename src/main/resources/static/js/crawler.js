var app = angular.module('crawler', ['ngResource', 'ngRoute']);

var crawlerController = app.controller('crawlerCtrl', function ($scope, $resource, $http, $location) {
    $scope.goInto = function (f) {
        var isDir = !f || f.dir;
        var url = $scope.getUrl(f);
        //var location = f && f.path;
        if (!isDir) {
            $scope.play(url);
        } else {
            $scope.deeper(f);
        }
    };

    $scope.deeper = function (f) {
        $http.get($scope.getUrl(f)).success(function (response) {
            $scope.ref = f && f.path;
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

    $scope.play = function (url) {
        if ($scope.player) {
            $scope.player.stop();
            $scope.player = null;
        }
        $scope.player = $scope.player || AV.Player.fromURL(url);
        $scope.player.play();
    };

    $scope.getUrl = function (f) {
        return '/path' + ((f && f.path) || '/') + (f && f.dir ? '.json' : '');
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