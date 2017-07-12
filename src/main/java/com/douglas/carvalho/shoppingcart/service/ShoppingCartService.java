package com.douglas.carvalho.shoppingcart.service;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	private static final String CART_ATTRIBUTE_NAME = "shoppingcart";

	@Autowired
	private HttpSession httpSession;
			
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	private ShoppingCart getShoppingCartInSession() {
		ShoppingCart shoppingCart = (ShoppingCart)this.httpSession.getAttribute(CART_ATTRIBUTE_NAME);
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart();
			this.httpSession.setAttribute(CART_ATTRIBUTE_NAME, shoppingCart);
		}
		return shoppingCart;
	}
	
	public ProductOrder addToCart(ProductOrder productOrder) {
		ShoppingCart shoppingCart = getShoppingCartInSession();
		ProductOrder orderProductInCart = shoppingCart.addToCart(productOrder);
		calculateAmount();
		return orderProductInCart;
	}
	
	public void removeFromCart(long productId) {
		ShoppingCart shoppingCart = getShoppingCartInSession();
		shoppingCart.removeProductFromCart(productId);
		calculateAmount();
	}
	
	private void calculateAmount(){
		ShoppingCart shoppingCart = getShoppingCartInSession();
		shoppingCart.setAmount(BigDecimal.valueOf(0));  
		
		for (ProductOrder productOrder : shoppingCart.getProductOrders()) {
			shoppingCart.setAmount(shoppingCart.getAmount().add(
					productOrder.getProduct().getPrice().multiply(BigDecimal.valueOf(productOrder.getQuantity()))));
			
		}
	}
	
	public void updateProductQuantity(ProductOrder productOrder){
		ShoppingCart shoppingCart = getShoppingCartInSession();
		shoppingCart.updateProductQuantity(productOrder);
		calculateAmount();
	}
	
	public ShoppingCart checkout(ShoppingCart shoppingCart){
		shoppingCart.getProductOrders().stream().forEach(p -> p.setShoppingCart(shoppingCart));
		return shoppingCartRepository.save(shoppingCart);
	}
	
	
	public int getNumberOfProductsInCart() {
		ShoppingCart shoppingCart = getShoppingCartInSession();
		List<ProductOrder> orderProducts = shoppingCart.getProductOrders();
		int productsInCart = 0;
		if (orderProducts!=null) {
			for (ProductOrder orderProduct: orderProducts) {
				productsInCart += orderProduct.getQuantity();
			}
		}
		return productsInCart;
	}
	
	public BigDecimal getProductsAmountInCart(){
		ShoppingCart shoppingCart = getShoppingCartInSession();
		return shoppingCart.getAmount();
	}

	public List<ProductOrder> getProductsInCart() {
		ShoppingCart shoppingCart = getShoppingCartInSession();
		return shoppingCart.getProductOrders();
	}
}
