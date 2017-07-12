(function() {
  angular.module('primeiraApp').controller('CartCtrl', [
    '$http',
    'restUrl',
    CartController
  ])

  function CartController($http, restUrl) {
    
	const vm = this
	vm.cart = {amount: 0, productOrders:[]};

    vm.checkout = function(){
	    $http.post(`${restUrl}/cart/purchase`, vm.cart)
    }
  }
})()
