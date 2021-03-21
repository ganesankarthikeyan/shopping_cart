package com.example.shopping_cart.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.shopping_cart.pojo.User;
import com.example.shopping_cart.interfaces.UserRepository;

@Repository
public class UserDao {

   @Autowired
   UserRepository userRepository;

   public boolean isRegisteredUser(String userId) {
      Optional<User> registerdUser = userRepository.findById(userId);
      System.out.println("registerdUser " + registerdUser);
      if (registerdUser.isPresent()) {
         return true;
      }
      return false;
   }

   public void register(User user) {
      userRepository.save(user);
   }

   public User isRegistered(String userId) {
      Optional<User> registerdUser = userRepository.findById(userId);
      if (registerdUser.isPresent()) {
         return registerdUser.get();
      }
      return null;
   }
}
