package com.douglas.carvalho.shoppingcart.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(method=RequestMethod.GET, value="/productsAmount")
	public BigDecimal getProductAmountInCart() {
		return this.shoppingCartService.getProductsAmountInCart();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/productsOrderList")
	public List<ProductOrder> getProductsOrderList() {
		return this.shoppingCartService.getProductsInCart();
	}
	
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/addToCart")
	public ProductOrder addToCart(@RequestBody Product product) {
		ProductOrder productOrder = new ProductOrder(product, 1);
		return shoppingCartService.addToCart(productOrder);		
	}
    
    @RequestMapping(method = RequestMethod.PUT, produces = "application/json", path = "/updateProductQuantity")
    public List<ProductOrder> updateProductQuantity(@RequestBody ProductOrder productOrder){
    	shoppingCartService.updateProductQuantity(productOrder);
		return shoppingCartService.getProductsInCart();
    }
    
	@RequestMapping(method=RequestMethod.DELETE, value="/deleteFromCart/{id}")
	public ResponseEntity deleteFromCartRest(@PathVariable(value = "id") Long productId) {
		shoppingCartService.removeFromCart(productId);
		return new ResponseEntity(HttpStatus.OK);
	}
    
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/checkout")
    public ShoppingCart checkout() {
    	ShoppingCart cart = shoppingCartService.checkout();
    	shoppingCartService.clearCurrentCart();
		return cart;
    }
    
}
