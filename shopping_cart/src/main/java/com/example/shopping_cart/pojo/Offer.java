package com.example.shopping_cart.pojo;

import org.springframework.data.annotation.Id;

public class Offer {
   @Id
   public String offerName;
   public int condition;
   public int profit;
   public String type;
   public boolean enabled;

   public void setOfferName(String offerName) {
      this.offerName = offerName;
   }

   public String getOfferName() {
      return offerName;
   }

   public void setCondition(int condition) {
      this.condition = condition;
   }

   public int getCondition() {
      return condition;
   }

   public void setProfit(int profit) {
      this.profit = profit;
   }

   public int getProfit() {
      return profit;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getType() {
      return type;
   }

   public void setEnabled(boolean enabled) {
      this.enabled = enabled;
   }

   public boolean isEnabled() {
      return enabled;
   }
}
