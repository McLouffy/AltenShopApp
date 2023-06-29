package com.alten.shop.alterShop.service;

import java.util.List;
import com.alten.shop.alterShop.model.Product;

public interface ProductService {
    Product createProduct(Product product);
    Product getProductById(Long id);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
    List<Product> getAllProducts();
   
}

