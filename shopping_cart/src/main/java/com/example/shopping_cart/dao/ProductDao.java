package com.example.shopping_cart.dao;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.shopping_cart.interfaces.ProductRepository;
import com.example.shopping_cart.pojo.Product;

@Repository
public class ProductDao {

   @Autowired
   ProductRepository productRepository;

   public Optional<Product> getProduct(String name) {
      return productRepository.findById(name);
   }

   public void save(Product dbProduct) {
      productRepository.save(dbProduct);
   }

   public ArrayList<Product> getProducts() {
      return (ArrayList<Product>) productRepository.findAll();
   }
}
