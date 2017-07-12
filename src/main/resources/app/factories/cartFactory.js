(function(){
  angular.module('primeiraApp').factory('cartFactory', [ 
	  '$http',
	  'restUrl',
	  CartFactory 
	])

  function CartFactory($http, restUrl) {
	  	
	function addToCart(product) {
		return $http.post(`${restUrl}/cart/addToCart`, product);
	};
	
	function getProductsCount() {
		return $http.get(`${restUrl}/cart/productsCount`);
	};

    return {addToCart, getProductsCount};
  }
})()