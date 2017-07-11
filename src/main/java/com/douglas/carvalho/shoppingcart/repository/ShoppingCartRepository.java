package com.douglas.carvalho.shoppingcart.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.douglas.carvalho.shoppingcart.domain.ShoppingCart;

/**
 * 11/07/2017
 * @author doug
 */
@Repository
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long>{}
