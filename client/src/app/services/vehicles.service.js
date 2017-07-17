/**
 * Created by Dell on 7/12/2017.
 */
(function() {
    'use strict';

    angular.module('plunker')
        .service('vehiclesService', vehiclesService);

    vehiclesService.$inject = ['$http', '$q', 'CONFIG'];

    function vehiclesService($http, $q, CONFIG) {

        var self = this;

        self.getVehicles = getVehicles;
        self.getVehicleById = getVehicleById;

        function getVehicles() {
            return $http.get(CONFIG.API_HOST+'/vehicles')
                .then(successFn, errorFn);
        }

        function getVehicleById(id) {
            return $http.get(CONFIG.API_HOST+'/vehicles/' + id)
                .then(successFn, errorFn);
        }

        function successFn(response) {
            return response.data;
        }

        function errorFn(response) {
            return $q.reject('ERROR: ' + response.statusText);
        }
    }

})();