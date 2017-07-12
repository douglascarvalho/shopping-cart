package com.douglas.carvalho.shoppingcart.controller;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.douglas.carvalho.shoppingcart.domain.Product;
import com.douglas.carvalho.shoppingcart.domain.ProductOrder;
import com.douglas.carvalho.shoppingcart.domain.ShoppingCart;
import com.douglas.carvalho.shoppingcart.service.ShoppingCartService;

/**
 * 10/07/2017
 * @author doug
 */
@RestController
@RequestMapping("/cart")
public class ShoppingCartController {
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@RequestMapping(method=RequestMethod.GET, value="/productsCount")
	public int getProductCountInCart() {
		return this.shoppingCartService.getNumberOfProductsInCart();
	}
	
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/addToCart")
	public ProductOrder addToCart(@RequestBody Product product) {
		//long productId = product.getId();
		//Product product = this.productRepository.findOne(productId);
		ProductOrder productOrder = new ProductOrder(product, 1);
		return shoppingCartService.addToCart(productOrder);		
	}
    
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/purchase")
    public ShoppingCart purchase(@RequestBody ShoppingCart cart) throws URISyntaxException{
    	ShoppingCart order = shoppingCartService.checkout(cart);
		return order;
    }
    
}
