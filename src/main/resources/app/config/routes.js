angular.module('primeiraApp').config([
  '$stateProvider',
  '$urlRouterProvider',
  function($stateProvider, $urlRouterProvider) {
    $stateProvider.state('products', {
      url: "/products",
      templateUrl: "product/products.html"
    }).state('cart', {
        url: "/cart",
        templateUrl: "cart/cart.html"
      })

    $urlRouterProvider.otherwise('/products')
  }
])
