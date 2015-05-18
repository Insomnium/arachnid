var app = angular.module('crawler', ['ngResource']);

var crawlerController = app.controller('crawlerCtrl', function ($scope, $resource, DirResource) {
    $scope.ls = DirResource.query();

});

crawlerController.factory('DirResource', function ($resource) {
    return $resource('/path/home/ins');
});