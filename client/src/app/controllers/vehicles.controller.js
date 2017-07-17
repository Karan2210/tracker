/**
 * Created by Dell on 7/12/2017.
 */
(function () {
    'use strict';

    angular.module('plunker')
        .controller('VehiclesController', VehiclesController);

    VehiclesController.$inject = ['vehiclesService'];

    function VehiclesController(vehiclesService) {
        var vehiclesVm = this;

        vehiclesVm.changeSort = changeSort;
        init();

        function init() {
            vehiclesVm.sorter = {
                by: 'highAlertCount',
                reverse: true
            };
            vehiclesService
                .getVehicles()
                .then(function (vehicles) {
                    vehiclesVm.vehicles = vehicles;
                }, function (error) {
                    console.log(error);
                });
        }

        function changeSort(prop) {
            vehiclesVm.sorter.by = prop;
            vehiclesVm.sorter.reverse = !vehiclesVm.sorter.reverse;
        }
    }
})();