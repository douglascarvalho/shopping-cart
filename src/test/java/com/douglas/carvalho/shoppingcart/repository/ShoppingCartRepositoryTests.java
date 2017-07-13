package com.douglas.carvalho.shoppingcart.repository;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.douglas.carvalho.shoppingcart.domain.Product;
import com.douglas.carvalho.shoppingcart.domain.ProductOrder;
import com.douglas.carvalho.shoppingcart.domain.ShoppingCart;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ShoppingCartRepositoryTests {
    
	@Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ShoppingCartRepository repository;

    @Test
    public void testInsert(){
        
    	ShoppingCart sp = new ShoppingCart();
    	ProductOrder po1 = new ProductOrder();
    	po1.setProduct(new Product("product1", BigDecimal.valueOf(2), "description", "imgUrl"));
    	po1.setQuantity(2);
    	sp.addToCart(po1);
    	
    	this.entityManager.persist(sp);
        
    	ShoppingCart spPersisted = this.repository.findOne(1L);
        
        assertThat(spPersisted.getProductOrders().size(), is(1));
        
        ProductOrder poPersisted = spPersisted.getProductOrders().get(0);
        assertThat(poPersisted.getProduct().getName(), is("product1"));
        assertThat(poPersisted.getQuantity(), is(2));
    }
    
}
