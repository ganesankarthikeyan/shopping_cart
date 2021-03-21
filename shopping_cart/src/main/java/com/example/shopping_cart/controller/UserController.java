package com.example.shopping_cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopping_cart.pojo.Constants;
import com.example.shopping_cart.pojo.User;
import com.example.shopping_cart.service.UserService;

@RestController
public class UserController {
   /*
   register and login of user for my reference
    */
   @Autowired
   UserService userService;

   @PostMapping("/register")
   @ResponseBody
   public String registerUser(@RequestBody User user) {
      //need to validate whether all the fields are there or not
      boolean isValidCredentials = userService.isValidCredentials(user);
      if (isValidCredentials) {
         boolean isPasswordsMatch = userService.isPasswordsMatch(user.getPassword(), user.getRepassword());
         if (isPasswordsMatch) {
            boolean isRegisteredUser = userService.isRegisterdUser(user.getUserId());
            if (isRegisteredUser) {
               return Constants.ALREADY_REGISTERED;
            } else {
               userService.register(user);
               return Constants.THANKS_FOR_REGISTERING;
            }
         } else {
            return Constants.PASSWORDS_MUST_MATCH;
         }
      } else {
         return Constants.CREDENTIALS_MUST_NOT_BE_EMPTY;
      }
   }

   @GetMapping("/login")
   @ResponseBody
   public String login(@RequestBody User user) {
      //need to validate login
      boolean isValidCredentials = userService.isValidLoginCredentials(user);
      if (isValidCredentials) {
         return userService.isRegistered(user.getUserId(), user.getPassword());
      } else {
         return Constants.CREDENTIALS_MUST_NOT_BE_EMPTY;
      }
   }

}
