/**
 * Created by Dell on 7/12/2017.
 */
(function() {
    'use strict';

    angular.module('plunker')
        .service('chartService', chartService);

    chartService.$inject = ['$http', '$q', 'CONFIG'];

    function chartService($http, $q, CONFIG) {

        var self = this;

        self.getReadingsByVin = getReadingsByVin;

        function getReadingsByVin(id, filter) {
            return $http.get(CONFIG.API_HOST+'/readings/' + id +'?filter='+filter)
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