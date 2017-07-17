/**
 * Created by Dell on 7/12/2017.
 */
(function () {
    "use strict";

    angular.module('plunker')
        .directive('chart', function () {
            return {
                restrict: 'E',
                template: '<div></div>',
                transclude: true,
                replace: true,
                scope: '=',
                controller: 'ChartController',
                controllerAs: 'chartVm',
                bindToController: true,
                link: function (scope, element, attrs) {
                    var opt = {
                        chart: {
                            renderTo: element[0],
                            type: 'line',
                            marginRight: 130,
                            marginBottom: 40
                        },
                        credits: {
                            enabled: false
                        },
                        title: {
                            text: attrs.title,
                            x: -20
                        },
                        subtitle: {
                            text: attrs.subtitle,
                            x: -20
                        },
                        xAxis: {
                            tickInterval: 1,
                            title: {
                                text: 'Date'
                            }
                        },
                        plotOptions: {
                            lineWidth: 0.5
                        },
                        yAxis: {
                            title: {
                                text: 'Reading'
                            },
                            tickInterval: (attrs.yinterval) ? new Number(attrs.yinterval) : null,
                            max: attrs.ymax,
                            min: attrs.ymin
                        },
                        tooltip: {
                            formatter: scope[attrs.formatter] || function () {
                                return '<b>' + this.y + '</b>'
                            }
                        },
                        legend: {
                            layout: 'vertical',
                            align: 'right',
                            verticalAlign: 'top',
                            x: -10,
                            y: 100,
                            borderWidth: 0
                        }
                    }

                    scope.$watch(function (scope) {
                        return JSON.stringify({
                            xAxis: {
                                categories: scope.chartVm.lineChartXData
                            },
                            series: scope.chartVm.lineChartYData
                        });
                    }, function (news) {
                        news = JSON.parse(news);
                        if (!news.series)return;
                        angular.extend(opt, news);
                        var chart = new Highcharts.Chart(opt);
                    });
                }
            }

        });
})();