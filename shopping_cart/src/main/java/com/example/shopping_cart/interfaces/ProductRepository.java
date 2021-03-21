package com.example.shopping_cart.interfaces;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.shopping_cart.pojo.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
