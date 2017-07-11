package com.douglas.carvalho.shoppingcart.service;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.carvalho.shoppingcart.domain.Product;
import com.douglas.carvalho.shoppingcart.domain.ProductOrder;
import com.douglas.carvalho.shoppingcart.domain.ShoppingCart;
import com.douglas.carvalho.shoppingcart.repository.ShoppingCartRepository;

/**
 * 10/07/2017
 * @author doug
 */
@Service
@Transactional
public class ShoppingCartService {
	
	private ShoppingCart shoppingCart = new ShoppingCart();
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	public ShoppingCart addToCart(Long productId) {
		
		Product product = productService.findById(productId);
		ProductOrder productOrder = new ProductOrder();
		productOrder.setShoppingCart(shoppingCart);
		productOrder.setProduct(product);
		productOrder.setQuantity(1);
		
		shoppingCart.addToCart(productOrder);
		
		updateCart();
		return shoppingCart;
	}
	
	private void updateCart(){
		shoppingCart.setAmount(BigDecimal.valueOf(0));  
		
		for (ProductOrder productOrder : shoppingCart.getProductOrders()) {
			shoppingCart.setAmount(shoppingCart.getAmount().add(
					productOrder.getProduct().getPrice().multiply(BigDecimal.valueOf(productOrder.getQuantity()))));
			
		}
	}
	
	public ShoppingCart purchase(){
		return shoppingCartRepository.save(shoppingCart);
	}
}
