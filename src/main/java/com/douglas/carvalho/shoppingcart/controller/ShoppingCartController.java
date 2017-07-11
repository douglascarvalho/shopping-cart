package com.douglas.carvalho.shoppingcart.controller;

import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	private ShoppingCartService orderService;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ShoppingCart getProducts(@RequestParam Long productId){
		return orderService.addToCart(productId);
	}
	
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/purchase")
    public ShoppingCart purchase(HttpServletRequest request) throws URISyntaxException{
    	ShoppingCart order = orderService.purchase();
    	
		//HttpHeaders header = new HttpHeaders();
		//header.setLocation(new URI(request.getRequestURL() + "/" + order.getId().toString()));

		return order;
    }
    
}
