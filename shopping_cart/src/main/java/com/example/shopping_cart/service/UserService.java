package com.example.shopping_cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopping_cart.dao.UserDao;
import com.example.shopping_cart.pojo.Constants;
import com.example.shopping_cart.pojo.User;

@Service
public class UserService {

   @Autowired
   UserDao userDao;

   public boolean isValidCredentials(User user) {
      if (isNullOrEmpty(user.getUserId()) || isNullOrEmpty(user.getPassword()) || isNullOrEmpty(user.getRepassword())) {
         return false;
      }
      return true;
   }

   public boolean isNullOrEmpty(String data) {
      if (data != null && !data.isEmpty()) {
         return false;
      }
      return true;
   }

   public boolean isPasswordsMatch(String password, String repassword) {
      if (password.equalsIgnoreCase(repassword)) {
         return true;
      }
      return false;
   }

   public boolean isRegisterdUser(String userId) {
      return userDao.isRegisteredUser(userId);
   }

   public void register(User user) {
      userDao.register(user);
   }

   public boolean isValidLoginCredentials(User user) {
      if (isNullOrEmpty(user.getUserId()) || isNullOrEmpty(user.getPassword())) {
         return false;
      }
      return true;
   }

   public String isRegistered(String userId, String password) {
      User dbUser = userDao.isRegistered(userId);
      if (dbUser == null) {
         return Constants.NOT_REGISTERED;
      } else {
         if (password.equalsIgnoreCase(dbUser.getPassword())) {
            return Constants.WELCOME;
         } else {
            return Constants.INCORRECT_PASSWORD;
         }
      }
   }
}
