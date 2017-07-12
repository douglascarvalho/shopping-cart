(function() {
  angular.module('primeiraApp').controller('ProductCtrl', [
    '$http',
    'restUrl',
    ProductController
  ])

  function ProductController($http, restUrl) {
    
	const vm = this
	vm.cart = {amount: 0, productOrders:[]};
	vm.amount = 0;
	
    vm.getSummary = function() {
      const url = `${restUrl}/products`
      $http.get(url).then(function(response) {
    	  vm.data = response.data
      })
      
    }
    
    vm.addItemToCart = function(product){
    	if (vm.cart.productOrders.length === 0){
    		const productOrder = createProductOrder(product);
    		vm.cart.productOrders.push(productOrder)
    	} else {
    		var repeate = false;
    		for(var i = 0; i < vm.cart.productOrders.length; i++){
    			if(vm.cart.productOrders[i].product.id === product.id){
    				repeate = true;
    				vm.cart.productOrders[i].quantity += 1;	
    			}
    		}
    		if (!repeate){
        		const productOrder = createProductOrder(product);
        		vm.cart.productOrders.push(productOrder)
    		}
    	}
    	
    	vm.cart.amount += parseFloat(product.price)
    }
    
    vm.checkout = function(){
	    $http.post(`${restUrl}/cart/purchase`, vm.cart)
    }

    function createProductOrder(product){
		const productOrder = {product:{}, quantity:0};
		productOrder.product = product;
		productOrder.quantity = 1;

		return productOrder;
    }
    
    vm.getSummary()
  }
})()
