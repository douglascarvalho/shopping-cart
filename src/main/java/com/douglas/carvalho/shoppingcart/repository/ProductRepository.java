package com.douglas.carvalho.shoppingcart.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.douglas.carvalho.shoppingcart.domain.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{}
