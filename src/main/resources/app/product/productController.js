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
	
    vm.init = function() {
      
      $http.get(`${restUrl}/products`).then(function(response) {
    	  vm.data = response.data
      })
      
      vm.updateCartCount();
      
    }
    
    vm.addItemToCart = function(product){
    	cartFactory.addToCart(product).then(function(data){
			vm.updateCartCount();
			msgs.addSuccess(product.name + " adicionado ao carrinho!");
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
    
    vm.init()
  }
})()
