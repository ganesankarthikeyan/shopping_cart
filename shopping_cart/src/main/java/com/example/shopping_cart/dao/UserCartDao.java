package com.example.shopping_cart.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.shopping_cart.pojo.UserCart;
import com.example.shopping_cart.interfaces.UserCartRepository;

@Repository
public class UserCartDao {

   @Autowired
   UserCartRepository userCartRepository;

   public UserCart getCart(String userId) {
      Optional<UserCart> userCart = userCartRepository.findById(userId);
      if (userCart.isPresent()) {
         return userCart.get();
      }
      return null;
   }

   public void save(UserCart userCart) {
      userCartRepository.save(userCart);
   }
}
