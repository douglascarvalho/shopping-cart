package com.douglas.carvalho.shoppingcart;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.douglas.carvalho.shoppingcart.domain.Product;
import com.douglas.carvalho.shoppingcart.domain.ProductOrder;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddToCartIntegrationTest {
    
	@Autowired
    private TestRestTemplate restTemplate;
	
	@Test
    public void addToCart() {
        ResponseEntity<ProductOrder> responseEntity =
            restTemplate.postForEntity("/cart/addToCart", new Product("productIntegration", BigDecimal.valueOf(2), "description", "imgUrl"), ProductOrder.class);
        ProductOrder po = responseEntity.getBody();
        
        assertThat(po.getProduct().getName(), is("productIntegration"));
        assertThat(po.getProduct().getPrice(), is(BigDecimal.valueOf(2)));
        assertThat(po.getQuantity(), is(1));        
    }
    

}
