package com.example.shopping_cart.pojo;

import java.util.ArrayList;

public class UserCartView {
   public String name;
   public int quantity;
   public int price;
   public String category;
   public int offeredPrice;
   public int offeredQuantity;
   public int totalSellingPrice;
   public int totalSellingQuantity;
   public ArrayList<Offer> offersApplied;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getQuantity() {
      return quantity;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }

   public int getPrice() {
      return price;
   }

   public void setPrice(int price) {
      this.price = price;
   }

   public String getCategory() {
      return category;
   }

   public void setCategory(String category) {
      this.category = category;
   }

   public int getOfferedPrice() {
      return offeredPrice;
   }

   public void setOfferedPrice(int offeredPrice) {
      this.offeredPrice = offeredPrice;
   }

   public int getOfferedQuantity() {
      return offeredQuantity;
   }

   public void setOfferedQuantity(int offeredQuantity) {
      this.offeredQuantity = offeredQuantity;
   }

   public int getTotalSellingPrice() {
      return totalSellingPrice;
   }

   public void setTotalSellingPrice(int totalSellingPrice) {
      this.totalSellingPrice = totalSellingPrice;
   }

   public void setTotalSellingQuantity(int totalSellingQuantity) {
      this.totalSellingQuantity = totalSellingQuantity;
   }

   public int getTotalSellingQuantity() {
      return totalSellingQuantity;
   }

   public void setOffersApplied(ArrayList<Offer> offersApplied) {
      this.offersApplied = offersApplied;
   }

   public ArrayList<Offer> getOffersApplied() {
      return offersApplied;
   }
}
