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
	
	function deleteOrderProduct(productOrder) {
		return $http.delete(`${restUrl}/cart/deleteFromCart/${productOrder.product.id}`, productOrder);
	};
	
	function updateProductOrder(productOrder) {
		return $http.put(`${restUrl}/cart/updateProductQuantity`, productOrder);
	};
	
	function checkout(){
		return $http.post(`${restUrl}/cart/checkout`);
	}
	
	function getProductsCount() {
		return $http.get(`${restUrl}/cart/productsCount`);
	};
	
	function getProductsAmount() {
		return $http.get(`${restUrl}/cart/productsAmount`);
	};
	
	function getOrderProductList() {
		return $http.get(`${restUrl}/cart/productsOrderList`);
	};

    return {addToCart, deleteOrderProduct, updateProductOrder, checkout, getProductsCount, getProductsAmount, getOrderProductList};
  }
})()