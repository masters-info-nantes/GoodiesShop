'use strict';

/**
 * @ngdoc function
 * @name ribFrontendApp.controller:MarketCtrl
 * @description
 * # MarketCtrl
 * Controller of the ribFrontendApp
 */
angular.module('boutiqueApp')
  .controller('shop', function () {

  $.soap({
    url: 'http://localhost:9763/services/Boutique/',
    namespaceURL:'http://boutique.goodies.services.fr'
});
$.soap({
    method: 'listerProduits',
    data: {
    },
    soap12: true,
    success: function (soapResponse) {
        // do stuff with soapResponse
        console.log(soapResponse);
        console.log(soapResponse.toString());
    },
    error: function (soapResponse) {
        console.log('that other server might be down...');
        console.log(soapResponse);
        console.log(soapResponse.toString());
    }
});


  });
