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

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTests {
    
	@Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository repository;

    @Test
    public void testInsert(){
        this.entityManager.persist(new Product("product", BigDecimal.valueOf(2), "description", "imgUrl"));
        List<Product> products = (List<Product>) this.repository.findAll();
        
        assertThat(products.size(), is(1));
        
        Product product = products.get(0);
        assertThat(product.getName(), is("product"));
        assertThat(product.getPrice(), is(BigDecimal.valueOf(2)));
    }
    
}
