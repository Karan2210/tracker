/**
 * Created by Dell on 7/12/2017.
 */
(function () {
    'use strict';

    angular.module('plunker')
        .controller('ChartController', ChartController);

    ChartController.$inject = ['chartService', '$routeParams'];

    function ChartController(chartService, $routeParams) {
        var chartVm = this;
        chartVm.changeFilter = changeFilter;
        init();

        function convertDate(input) {
            var date = new Date(input);
            var hours = date.getHours();
            var minutes = "0" + date.getMinutes();
            var seconds = "0" + date.getSeconds();
            var day = date.getDate();
            var month = date.getMonth() + 1;
            var year = date.getUTCFullYear();
            return month + '-' + day + '-' + year + '-' + hours + ':' + minutes.substr(-2) + ':' + seconds.substr(-2);
        }

        function setData(readings, xData, yData) {
            angular.forEach(readings, function (reading) {
                xData.push(convertDate(reading.timestamp));
                yData[0].data.push(reading.fuelVolume);
                yData[1].data.push(reading.engineRpm);
                yData[2].data.push(reading.speed);
                yData[3].data.push(reading.engineHp);
            });

            chartVm.lineChartYData = yData;
            chartVm.lineChartXData = xData;

            if (xData.length > 0) {
                chartVm.showChart = true;
                chartVm.showError = false;
            } else {
                chartVm.showChart = false;
                chartVm.showError = true;
            }
        }

        function init() {
            chartVm.filter = {"name": "last1d"};
            chartVm.showChart = false;
            chartVm.showError = false;
            chartService
                .getReadingsByVin($routeParams.id, chartVm.filter.name)
                .then(function (readings) {
                    var xData = [],
                        yData = [{"name": "Fuel Volume", "data": []},
                            {"name": "Engine Rpm", "data": []},
                            {"name": "Speed", "data": []},
                            {"name": "Engine Horse Power", "data": []}];
                    setData(readings, xData, yData);
                }, function (error) {
                    console.log(error);
                });
        }

        function changeFilter(filter) {
            chartService
                .getReadingsByVin($routeParams.id, filter)
                .then(function (readings) {
                    var xData = [],
                        yData = [{"name": "Fuel Volume", "data": []},
                            {"name": "Engine Rpm", "data": []},
                            {"name": "Speed", "data": []},
                            {"name": "Engine Horse Power", "data": []}];
                    setData(readings, xData, yData);
                }, function (error) {
                    console.log(error);
                });
        }
    }
})();