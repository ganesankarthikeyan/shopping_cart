package com.example.shopping_cart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopping_cart.dao.ProductDao;
import com.example.shopping_cart.dao.UserCartDao;
import com.example.shopping_cart.pojo.Constants;
import com.example.shopping_cart.pojo.Offer;
import com.example.shopping_cart.pojo.Product;
import com.example.shopping_cart.pojo.UserCart;
import com.example.shopping_cart.pojo.UserCartView;

@Service
public class ProductService {

   @Autowired
   ProductDao productDao;

   @Autowired
   UserCartDao userCartDao;

   public Product deductProductFromDB(String name, int quantity) {
      Optional<Product> product = productDao.getProduct(name);
      if (product.isPresent()) {
         Product dbProduct = product.get();
         dbProduct.setQuantity(dbProduct.getQuantity() - quantity);
         productDao.save(dbProduct);
         return dbProduct;
      }
      return null;
   }

   public void add(Product product) {
      productDao.save(product);
   }

   public UserCartView addProductInCart(ArrayList<Offer> offers, Product product, String userId, Product dbProduct) {
      ArrayList<Offer> appOffers = new ArrayList<>();
      //offeredProduct is the product after the offers are applied......
      Product offeredProduct = new Product();

      offeredProduct.setName(product.getName());
      //using dbproduct to set category
      offeredProduct.setCategory(dbProduct.getCategory());
      offeredProduct.setQuantity(product.getQuantity());
      /*
      using dbproduct to set price, as user has no rights to change price
      using default price, if any offer is not applied
       */
      offeredProduct.setPrice(dbProduct.getPrice() * product.getQuantity());
      product.setPrice(dbProduct.getPrice() * product.getQuantity());

      for (Offer offer : offers) {
         //checking whether offer is enabled at that instance
         if (offer.isEnabled()) {
            if (product.getQuantity() >= product.getQuantity()) {
               //offer is applicable
               appOffers.add(offer);
               if (Constants.DISCOUNT.equalsIgnoreCase(offer.getType())) {
                  offeredProduct.setPrice((dbProduct.getPrice() * product.getQuantity()) - ((dbProduct.getPrice() * product.getQuantity() * offer.getProfit()) / 100));
                  offeredProduct.setOfferedPrice(((dbProduct.getPrice() * product.getQuantity() * offer.getProfit()) / 100));
               } else if (Constants.ADD.equalsIgnoreCase(offer.getType())) {
                  offeredProduct.setQuantity(product.getQuantity() + offer.getProfit());
                  offeredProduct.setOfferedQuantity(offer.getProfit());
               }
            }
         }
      }
      
      /*
         now appOffers have all the applicable offers
         need to update product along with offers in userscart
       */

      //getting products from userCart
      UserCart userCart = userCartDao.getCart(userId);
      if (userCart == null) {
         //user is adding product for first time, so initializing the object

         ArrayList<Product> products = new ArrayList<>();
         HashMap<String, Product> offeredProducts = new HashMap<>();
         HashMap<String, ArrayList<Offer>> offersForProduct = new HashMap<>();

         userCart = new UserCart();
         userCart.setUserName(userId);
         userCart.setProducts(products);
         userCart.setOfferedProduct(offeredProducts);
         userCart.setOffersForProduct(offersForProduct);

      }
      //adding product into cart
      ArrayList<Product> products = userCart.getProducts();
      products.add(product);
      userCart.setProducts(products);

      //adding product after offer applied
      HashMap<String, Product> offeredProductFromCart = userCart.getOfferedProduct();
      offeredProductFromCart.put(product.getName(), offeredProduct);

      //adding offers to the product
      HashMap<String, ArrayList<Offer>> offersForProduct = userCart.getOffersForProduct();
      offersForProduct.put(product.getName(), appOffers);

      //added necessary data, now saving in DB
      userCartDao.save(userCart);

      return convertToView(offeredProduct, appOffers);
   }

   private UserCartView convertToView(Product offeredProduct, ArrayList<Offer> appOffers) {
      UserCartView view = new UserCartView();
      view.setName(offeredProduct.getName());
      view.setPrice(offeredProduct.getPrice() + offeredProduct.getOfferedPrice());
      view.setCategory(offeredProduct.getCategory());
      view.setQuantity(offeredProduct.getQuantity() - offeredProduct.getOfferedQuantity());
      view.setOfferedPrice(offeredProduct.getOfferedPrice());
      view.setOfferedQuantity(offeredProduct.getOfferedQuantity());
      view.setTotalSellingPrice(offeredProduct.getPrice());
      view.setTotalSellingQuantity(offeredProduct.getQuantity());
      view.setOffersApplied(appOffers);
      return view;
   }

   public ArrayList<UserCartView> revalidateOffersAndGetCartDetails(String userId, ArrayList<Offer> offers) {

      //we can do a normal get and show the cart details
      //but we will do a recheck of already applied offers for its validity, before showing
      UserCart userCart = userCartDao.getCart(userId);

      ArrayList<UserCartView> views = new ArrayList<>();
      if (userCart != null) {
         ArrayList<Product> products = userCart.getProducts();
         for (Product product : products) {

            Product dbProduct = productDao.getProduct(product.getName()).get();

            Product offeredProduct = new Product();

            offeredProduct.setName(product.getName());
            //using dbproduct to set category
            offeredProduct.setCategory(dbProduct.getCategory());
            offeredProduct.setQuantity(product.getQuantity());
            offeredProduct.setPrice(product.getPrice());

            ArrayList<Offer> applicableOffers = new ArrayList<>();
            for (Offer offer : offers) {
               if (offer.isEnabled()) {
                  if (product.getQuantity() >= offer.getCondition()) {
                     applicableOffers.add(offer);
                     if (Constants.DISCOUNT.equalsIgnoreCase(offer.getType())) {
                        offeredProduct.setPrice((dbProduct.getPrice() * product.getQuantity()) - ((dbProduct.getPrice() * product.getQuantity() * offer.getProfit()) / 100));
                        offeredProduct.setOfferedPrice(((dbProduct.getPrice() * product.getQuantity() * offer.getProfit()) / 100));
                     } else if (Constants.ADD.equalsIgnoreCase(offer.getType())) {
                        offeredProduct.setQuantity(product.getQuantity() + offer.getProfit());
                        offeredProduct.setOfferedQuantity(offer.getProfit());
                     }
                  }
               }
            }
            HashMap<String, Product> offeredProductFromCart = userCart.getOfferedProduct();
            offeredProductFromCart.put(product.getName(), offeredProduct);

            HashMap<String, ArrayList<Offer>> offersForProduct = userCart.getOffersForProduct();
            offersForProduct.put(product.getName(), applicableOffers);
            userCartDao.save(userCart);
            views.add(convertToView(offeredProduct, applicableOffers));
         }
         return views;
      }
      return null;

   }

   public ArrayList<UserCartView> getCartDetails(String userId) {
      ArrayList<UserCartView> userCart = new ArrayList<>();
      UserCart cart = userCartDao.getCart(userId);

      if(cart != null){
         for (Product product : cart.getProducts()) {
            userCart.add(convertToView(cart.getOfferedProduct().get(product.getName()), cart.getOffersForProduct().get(product.getName())));
         }
      }

      return userCart;
   }

   public ArrayList<Product> getProducts() {
      return productDao.getProducts();
   }
}
