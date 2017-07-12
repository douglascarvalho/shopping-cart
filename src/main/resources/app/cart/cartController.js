(function() {
  angular.module('primeiraApp').controller('CartCtrl', [
    '$http',
    'restUrl',
    'cartFactory',
    'msgs',
    CartController
  ])

  function CartController($http, restUrl, cartFactory, msgs) {
    
	const vm = this
	
	vm.cart = {amount: 0, productOrders:[]};
	
    vm.init = function() {
    	cartFactory.getOrderProductList().then(function(response) {
			vm.productOrders = response.data;
		});
		
		vm.updateCartCount();
		vm.updateCartAmount();
	}

	vm.updateCartCount = function() {
		cartFactory.getProductsCount().then(function(response) {
			vm.productsCountInCart = response.data;
		});		
	};
	
	vm.updateCartAmount = function() {
		cartFactory.getProductsAmount().then(function(response) {
			vm.productsAmountInCart = response.data;
		});
	};

	vm.deleteProductOrder = function(productOrder) {
		cartFactory.deleteOrderProduct(productOrder).then(function(response){
			vm.productOrders = response.data;
			vm.updateCartCount();
			vm.updateCartAmount();
			msgs.addSuccess("Deleted order for product " + productOrder.product.name );
		}).error(function(data, status, headers, config) {
	        msgs.addError(response.data)
		});
	};

    vm.checkout = function(){
	    $http.post(`${restUrl}/cart/purchase`, vm.cart)
    }
    
    vm.init()
  }
})()
