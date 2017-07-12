(function() {
  angular.module('primeiraApp').controller('CartCtrl', [
    '$http',
    '$location',
    'restUrl',
    'cartFactory',
    'msgs',
    CartController
  ])

  function CartController($http, $location, restUrl, cartFactory, msgs) {
    
	const vm = this
		
    vm.init = function() {
    	vm.updateProductOrderList();
		vm.updateCartAmount();
	}
    
    vm.updateProductOrderList = function() {
    	cartFactory.getOrderProductList().then(function(response) {
			vm.productOrders = response.data;
		});
    }
	
	vm.updateCartAmount = function() {
		cartFactory.getProductsAmount().then(function(response) {
			vm.productsAmountInCart = response.data;
		});
	};

	vm.deleteProductOrder = function(productOrder) {
		cartFactory.deleteOrderProduct(productOrder).then(function(response){
			vm.updateProductOrderList();
			vm.updateCartAmount();
			msgs.addWarning(productOrder.product.name + " removido do carrinho!" );
		}).catch(function(response) {
	        msgs.addError(response.data)
		});
	};
	
	vm.updateProductOrder = function(productOrder) {
		if(!angular.isNumber(productOrder.quantity)) {
			productOrder.quantity = 1;
		}
		
		cartFactory.updateProductOrder(productOrder).then(function(response){
			vm.productOrders = response.data;
			vm.updateCartAmount();
		}).catch(function(response) {
	        msgs.addError(response.data)
		});
	};

    vm.checkout = function(){
	    cartFactory.checkout().then(function(response){
			vm.order = response.data
			msgs.addSuccess("Compra de Id: " + vm.order.id + " no valor de " + vm.order.amount + " registrada no sistema!");
			$location.path("/products");
	    }).catch(function(response) {
	        msgs.addError(response.data)
		});
	    
    };
    
    vm.init()
  }
})()
