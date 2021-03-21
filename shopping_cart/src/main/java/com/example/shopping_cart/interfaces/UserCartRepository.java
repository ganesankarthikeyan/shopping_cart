package com.example.shopping_cart.interfaces;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.shopping_cart.pojo.UserCart;

public interface UserCartRepository extends MongoRepository<UserCart, String> {
}
