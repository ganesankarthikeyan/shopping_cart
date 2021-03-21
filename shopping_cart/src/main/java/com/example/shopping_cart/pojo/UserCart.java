package com.example.shopping_cart.pojo;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.data.annotation.Id;

public class UserCart {
   @Id
   public String userName;

   public ArrayList<Product> products;

   public HashMap<String, ArrayList<Offer>> offersForProduct;

   public HashMap<String, Product> offeredProduct;

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getUserName() {
      return userName;
   }

   public void setProducts(ArrayList<Product> products) {
      this.products = products;
   }

   public ArrayList<Product> getProducts() {
      return products;
   }

   public void setOffersForProduct(HashMap<String, ArrayList<Offer>> offersForProduct) {
      this.offersForProduct = offersForProduct;
   }

   public HashMap<String, ArrayList<Offer>> getOffersForProduct() {
      return offersForProduct;
   }

   public void setOfferedProduct(HashMap<String, Product> offeredProduct) {
      this.offeredProduct = offeredProduct;
   }

   public HashMap<String, Product> getOfferedProduct() {
      return offeredProduct;
   }
}
