/**
 * Created by Dell on 7/12/2017.
 */
(function () {
    "use strict";
    angular.module("plunker")
        .controller("MaponeController", MaponeController);

    MaponeController.$inject = ['chartService', '$routeParams'];

    function MaponeController(chartService, $routeParams) {
        var maponeVm = this;
        init();

        function init() {
            maponeVm.map = {
                center: {
                    latitude: 41.8460142,
                    longitude:-87.6482463
                },
                zoom: 11,
                markers: []
            };

            chartService
                .getReadingsByVin($routeParams.id, 'last30min')
                .then(function (readings) {

                    var xData = [];
                    angular.forEach(readings, function (reading, key) {
                        xData.push({
                            coords: {
                                latitude: reading.latitude,
                                longitude: reading.longitude
                            },
                            id: key,
                            icon: 'https://maps.google.com/mapfiles/ms/icons/green-dot.png'
                        });
                    });
                    if (xData.length > 0) {
                        maponeVm.showMap = true;
                        maponeVm.showError = false;
                    } else {
                        maponeVm.showMap = false;
                        maponeVm.showError = true;
                    }
                    maponeVm.map.markers = xData;
                }, function (error) {
                    console.log(error);
                });
        }

    }
})();