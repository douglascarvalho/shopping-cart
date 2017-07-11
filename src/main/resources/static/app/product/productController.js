(function() {
  angular.module('primeiraApp').controller('ProductCtrl', [
    '$http',
    'restUrl',
    ProductController
  ])

  function ProductController($http, restUrl) {
    const vm = this
    vm.getSummary = function() {
      const url = `${restUrl}/products`
      $http.get(url).then(function(response) {
    	  vm.data = response.data
      })
      
    }

    vm.getSummary()
  }
})()
