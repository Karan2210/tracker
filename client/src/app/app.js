/**
 * Created by Dell on 7/12/2017.
 */
(function () {
    'use strict';

    angular
        .module('plunker', ['ngRoute', 'angularUtils.directives.dirPagination', 'uiGmapgoogle-maps'])
        .config(moduleConfig);

    moduleConfig.$inject = ['$routeProvider'];

    function moduleConfig($routeProvider) {

        $routeProvider
            .when('/map/:id', {
                templateUrl: '/app/views/mapone.tmpl.html',
                controller: 'MaponeController',
                controllerAs: 'maponeVm'
            })
            .when('/vehicles', {
                templateUrl: '/app/views/vehicles.tmpl.html',
                controller: 'VehiclesController',
                controllerAs: 'vehiclesVm'
            })
            .when('/alerts/:id', {
                templateUrl: '/app/views/alerts.tmpl.html',
                controller: 'AlertsController',
                controllerAs: 'alertsVm'
            })
            .when('/graphs/:id', {
                templateUrl: '/app/views/chart.tmpl.html',
                controller: 'ChartController',
                controllerAs: 'chartVm'
            })
            .otherwise({
                redirectTo: '/vehicles'
            });
    }

})();
