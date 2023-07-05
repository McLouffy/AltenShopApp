package com.alten.shop.alterShop.service;

import java.util.List;
import java.util.Map;

import com.alten.shop.alterShop.dto.ProductDto;

public interface ProductService {
    ProductDto createProduct(ProductDto productDTO);
    ProductDto getProductById(Long id);
    ProductDto updateProduct(Long id, ProductDto productDTO);
    void deleteProduct(Long id);
    List<ProductDto> getAllProducts();
    ProductDto updateOneFieldInProduct(ProductDto productDto, Long id);
    
}

