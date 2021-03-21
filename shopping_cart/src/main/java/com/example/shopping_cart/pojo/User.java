package com.example.shopping_cart.pojo;

import org.springframework.data.annotation.Id;

public class User {
   @Id
   public String userId;
   public String password;
   public String repassword;

   public void setUserId(String userId) {
      this.userId = userId;
   }

   public String getUserId() {
      return userId;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getPassword() {
      return password;
   }

   public void setRepassword(String repassword) {
      this.repassword = repassword;
   }

   public String getRepassword() {
      return repassword;
   }
}
