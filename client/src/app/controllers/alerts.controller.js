/**
 * Created by Dell on 7/12/2017.
 */
(function () {
    'use strict';

    angular.module('plunker')
        .controller('AlertsController', AlertsController);

    AlertsController.$inject = ['alertsService', '$routeParams'];

    function AlertsController(alertsService, $routeParams) {
        var alertsVm = this;

        alertsVm.changeSort = changeSort;
        init();

        function init() {
            alertsVm.sorter = {
                by: 'timestamp',
                reverse: true
            };
            alertsService
                .getAlertsById($routeParams.id)
                .then(function (alerts) {
                    alertsVm.alerts = alerts;
                }, function (error) {
                    console.log(error);
                });
        }

        function changeSort(prop) {
            alertsVm.sorter.by = prop;
            alertsVm.sorter.reverse = !alertsVm.sorter.reverse;
        }
    }
})();