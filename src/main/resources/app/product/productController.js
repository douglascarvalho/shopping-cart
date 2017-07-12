(function() {
  angular.module('primeiraApp').controller('ProductCtrl', [
    '$http',
    'restUrl',
    'cartFactory',
    'msgs',
    ProductController
  ])

  function ProductController($http, restUrl, cartFactory, msgs) {
    
	const vm = this
	vm.cart = {amount: 0, productOrders:[]};
	vm.amount = 0;
	
	vm.productsCountInCart = 0;
	
    vm.getSummary = function() {
      const url = `${restUrl}/products`
      $http.get(url).then(function(response) {
    	  vm.data = response.data
      })
      
    }
    
    vm.addItemToCart = function(product){
    	cartFactory.addToCart(product).then(function(data){
			vm.updateCartCount();
			msgs.addSuccess("Added " + product.name + " to Cart!");
		}).catch(function(response) {
	        msgs.addError(response.data)
		});
    }
    
	vm.updateCartCount = function() {
		cartFactory.getProductsCount().then(function(response) {
			vm.productsCountInCart = response.data;
		});		
	};

    function createProductOrder(product){
		const productOrder = {product:{}, quantity:0};
		productOrder.product = product;
		productOrder.quantity = 1;

		return productOrder;
    }
    
    vm.getSummary()
  }
})()
