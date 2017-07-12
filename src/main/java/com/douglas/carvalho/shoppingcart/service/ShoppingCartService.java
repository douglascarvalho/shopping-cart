package com.douglas.carvalho.shoppingcart.service;

import java.math.BigDecimal;
import java.util.List;

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
	
	public ProductOrder addToCart(ProductOrder productOrder) {
		ProductOrder orderProductInCart = shoppingCart.addToCart(productOrder);
		updateCart();
		return orderProductInCart;
	}
	
	public void removeFromCart(long productId) {
		shoppingCart.removeProductFromCart(productId);
		updateCart();
	}
	
	private void updateCart(){
		shoppingCart.setAmount(BigDecimal.valueOf(0));  
		
		for (ProductOrder productOrder : shoppingCart.getProductOrders()) {
			shoppingCart.setAmount(shoppingCart.getAmount().add(
					productOrder.getProduct().getPrice().multiply(BigDecimal.valueOf(productOrder.getQuantity()))));
			
		}
	}
	
	public ShoppingCart checkout(ShoppingCart shoppingCart){
		shoppingCart.getProductOrders().stream().forEach(p -> p.setShoppingCart(shoppingCart));
		return shoppingCartRepository.save(shoppingCart);
	}
	
	
	public int getNumberOfProductsInCart() {
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
		return shoppingCart.getAmount();
	}

	public List<ProductOrder> getProductsInCart() {
		return shoppingCart.getProductOrders();
	}
}
