package com.example.shopping_cart.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopping_cart.dao.OfferDao;
import com.example.shopping_cart.pojo.Offer;

@Service
public class OfferService {

   @Autowired
   OfferDao offerDao;

   public ArrayList<Offer> getOffers() {
      return offerDao.getOffers();
   }

   public void add(Offer offer) {
      offerDao.add(offer);
   }

   public Offer getOffer(String offerName) {
      Optional<Offer> offer = offerDao.get(offerName);
      if (offer.isPresent()) {
         return offer.get();
      }
      return null;
   }

   public void updateOfferStatus(Offer offer, boolean status) {
      offer.setEnabled(status);
      offerDao.add(offer);
   }
}
