package com.example.shopping_cart.interfaces;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.shopping_cart.pojo.Offer;

public interface OfferRepository extends MongoRepository<Offer, String> {
}
