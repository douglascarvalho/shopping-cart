package com.douglas.carvalho.shoppingcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.douglas.carvalho.shoppingcart.service.ProductService;

/**
 * 10/07/2017
 * @author doug
 */
@SpringBootApplication
public class ShoppingCartApplication {

	public static void main(String[] args) {		
		ConfigurableApplicationContext applicationContext = SpringApplication.run(ShoppingCartApplication.class, args);

		ProductService productService = applicationContext.getBean(ProductService.class);
		productService.saveInitialBatch();
	}
}
