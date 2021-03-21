package com.example.shopping_cart.pojo;

import org.springframework.data.annotation.Id;

public class Product {
   @Id
   public String name;
   public int price;
   public int quantity;
   public String category;
   public int offeredPrice;
   public int offeredQuantity;

   public void setName(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   public void setPrice(int price) {
      this.price = price;
   }

   public int getPrice() {
      return price;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }

   public int getQuantity() {
      return quantity;
   }

   public void setCategory(String category) {
      this.category = category;
   }

   public String getCategory() {
      return category;
   }

   public void setOfferedPrice(int offeredPrice) {
      this.offeredPrice = offeredPrice;
   }

   public int getOfferedPrice() {
      return offeredPrice;
   }

   public void setOfferedQuantity(int offeredQuantity) {
      this.offeredQuantity = offeredQuantity;
   }

   public int getOfferedQuantity() {
      return offeredQuantity;
   }
}
