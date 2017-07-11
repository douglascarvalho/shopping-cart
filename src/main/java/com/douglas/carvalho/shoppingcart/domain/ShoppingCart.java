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

	public void addToCart(ProductOrder productOrder) {
		if (productOrders != null && productOrders.contains(productOrder)) {
			productOrders.stream()
					.filter(s -> s.getProduct().getId() == productOrder.getProduct().getId())
					.forEach(s -> s.setQuantity(s.getQuantity()+1));
		} else {
			if (productOrders == null) {
				productOrders = new ArrayList<>();
			}
			productOrders.add(productOrder);
		}
	}
	
}