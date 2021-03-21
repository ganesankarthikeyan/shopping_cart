package com.example.shopping_cart.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopping_cart.pojo.Offer;
import com.example.shopping_cart.service.OfferService;

@RestController
public class OfferController {

   @Autowired
   OfferService offerService;

   @PostMapping("/addOffer")
   public void addOffer(@RequestBody Offer offer) {
      offerService.add(offer);
   }

   @PostMapping("/updateOfferStatus/{offerName}/{status}")
   public void updateOfferStatus(@PathVariable String offerName, @PathVariable boolean status) {
      Offer offer = offerService.getOffer(offerName);
      if (offer != null) {
         offerService.updateOfferStatus(offer, status);
      }
   }

   @GetMapping("/getOffers")
   public ArrayList<Offer> getOffers(){
      return offerService.getOffers();
   }
}
