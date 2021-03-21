package com.example.shopping_cart.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopping_cart.pojo.Offer;
import com.example.shopping_cart.pojo.Product;
import com.example.shopping_cart.pojo.UserCartView;
import com.example.shopping_cart.service.OfferService;
import com.example.shopping_cart.service.ProductService;

@RestController
public class ProductController {

   @Autowired
   ProductService productService;

   @Autowired
   OfferService offerService;

   @PostMapping("/addProductToUser/{userId}")
   public UserCartView addProductToUser(@PathVariable String userId, @RequestBody Product product) {
      //considering only valid users enters the system.
      //considering the products are always available in the system.

      //I am reducing the quantity from db while he is adding in cart......
      Product dbProduct = productService.deductProductFromDB(product.getName(), product.getQuantity());

      /*
      check for offers
      getting all the offers and checking whether any offer applies while adding this product in cart
       */
      ArrayList<Offer> offers = offerService.getOffers();
      if (dbProduct != null) {
         return productService.addProductInCart(offers, product, userId, dbProduct);
      }

      return null;
   }

   @PostMapping("/addProduct")
   public void addProduct(@RequestBody Product product) {
      //adding product into DB......
      productService.add(product);
   }

   @GetMapping("/revalidateOffersAndGetCartDetails/{userId}")
   public ArrayList<UserCartView> revalidateOffersAndGetCartDetails(@PathVariable String userId) {
      ArrayList<Offer> offers = offerService.getOffers();
      return productService.revalidateOffersAndGetCartDetails(userId, offers);
   }

   @GetMapping("/getCartDetails/{userId}")
   public ArrayList<UserCartView> getCartDetails(@PathVariable String userId) {
      return productService.getCartDetails(userId);
   }

   @GetMapping("/getProducts")
   public ArrayList<Product> getProducts(){
      return productService.getProducts();
   }
}
