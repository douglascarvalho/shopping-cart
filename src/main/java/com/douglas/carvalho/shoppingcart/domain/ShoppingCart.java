package com.douglas.carvalho.shoppingcart.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 10/07/2017
 * @author doug
 */
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {
    
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private BigDecimal amount;
    
    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL)
    private List<ProductOrder> productOrders;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public List<ProductOrder> getProductOrders() {
		return productOrders;
	}

	public void setProductOrders(List<ProductOrder> productOrders) {
		this.productOrders = productOrders;
	}

	public ProductOrder addToCart(ProductOrder productOrder) {
		if (productOrders != null && productOrders.contains(productOrder)) {
			ProductOrder oldProductOrder = 
					productOrders.stream()
					.filter(s -> s.getProduct().getId() == productOrder.getProduct().getId())
					.findFirst().orElse(null);
					
			oldProductOrder.setQuantity(oldProductOrder.getQuantity()+1);
			return oldProductOrder;
		} else {
			if (productOrders == null) {
				productOrders = new ArrayList<>();
			}
			productOrders.add(productOrder);
			
			return productOrder;
		}
	}
	
	
	public void removeProductFromCart(Long productId) {
		ProductOrder productOrder = 
				productOrders.stream()
				.filter(s -> s.getProduct().getId() == productId)
				.findFirst().orElse(null);
		
		if (productOrder != null) {
			productOrders.remove(productOrder);
		}
	}
	
	public void updateProductQuantity(ProductOrder updatedProductOrder) {
		ProductOrder productOrder = 
				productOrders.stream()
				.filter(s -> s.getProduct().getId() == updatedProductOrder.getProduct().getId())
				.findFirst().orElse(null);

		if (productOrder != null) {
			productOrder.setQuantity(updatedProductOrder.getQuantity());
		}
		
	}
	
}
