package com.example.shopping_cart.interfaces;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.shopping_cart.pojo.User;

public interface UserRepository extends MongoRepository<User, String> {
}
