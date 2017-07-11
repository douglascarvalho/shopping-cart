package com.douglas.carvalho.shoppingcart.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.carvalho.shoppingcart.domain.Product;
import com.douglas.carvalho.shoppingcart.repository.ProductRepository;

/**
 * 10/07/2017
 * @author doug
 */
@Service
@Transactional
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
    private static List<Product> products = new ArrayList<>();

    static {
        products.add(new Product("Café", BigDecimal.valueOf(16.00)));
        products.add(new Product("Óleo de coco", BigDecimal.valueOf(46.90)));
        products.add(new Product("Aveia", BigDecimal.valueOf(6.75)));
        products.add(new Product("Brócolis", BigDecimal.valueOf(5.00)));
        products.add(new Product("Tribulus Terrestris", BigDecimal.valueOf(40.99)));
    }

    public void saveInitialBatch(){
        productRepository.save(products);
    }
    
    public List<Product> findAll(){
    	return (List<Product>) productRepository.findAll();
    }
}
