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
        products.add(new Product("Café", BigDecimal.valueOf(16.00), "Delicioso Café", "https://shoppub.s3.amazonaws.com/native/media/cache/7a/b5/7ab5d4198d8f059b5a8d3b77433e2f7d.jpg"));
        products.add(new Product("Óleo de coco", BigDecimal.valueOf(46.90),"Saudável Óleo de Coco", "https://static.natue.com.br/BR/product/oleo-de-coco-500ml-copra-coco-2481-4058-1842-1-product.jpg"));
        products.add(new Product("Aveia", BigDecimal.valueOf(6.75), "Aveia proteíca", "https://media.natue.com.br/Artigos/aveia.png"));
        products.add(new Product("Quinoa", BigDecimal.valueOf(10.00),"Quinoa proteíca", "https://mentesacorposao.com/wp-content/uploads/2017/03/Quinoa.jpg"));
        products.add(new Product("Chia", BigDecimal.valueOf(11.00), "Chia protéica", "https://static.tuasaude.com/img/be/ne/beneficios-da-chia-1-640-427.jpg"));
        products.add(new Product("Pasta de Amendoin", BigDecimal.valueOf(25.00), "Deliciosa Pasta de Amendoin", "http://multiproteinas.com.br/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/p/a/pasta_de_amendoim_1.jpeg"));
        products.add(new Product("Couve-Manteiga", BigDecimal.valueOf(3.50), "Deliciosa Couve-Manteiga", "http://www.varanda.com.br/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/f/i/file_90_34_1.jpg"));
        products.add(new Product("Brócolis", BigDecimal.valueOf(5.00), "Delicioso Brócolis", "http://www.extraplus.com.br/media/W1siZiIsIjIwMTIvMTAvMDMvMTFfMjhfNDVfMjY0XzI5NjE1MV9Ccl9jb2xpc19VbmQuLmpwZyJdXQ/296151-Br%C3%B3colis-Und..jpg"));
    }

    public void saveInitialBatch(){
        productRepository.save(products);
    }
    
    public List<Product> findAll(){
    	return (List<Product>) productRepository.findAll();
    }
    
    public Product findById(Long productId){
    	return productRepository.findOne(productId);
    }
}
