package com.example.shopping_cart.dao;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.shopping_cart.pojo.Offer;
import com.example.shopping_cart.interfaces.OfferRepository;

@Repository
public class OfferDao {

   @Autowired
   OfferRepository offerRepository;

   public ArrayList<Offer> getOffers() {
      ArrayList<Offer> offers = (ArrayList<Offer>) offerRepository.findAll();
      return offers;
   }

   public void add(Offer offer) {
      offerRepository.save(offer);
   }

   public Optional<Offer> get(String offerName) {
      Optional<Offer> offer = offerRepository.findById(offerName);
      return offer;
   }
}
