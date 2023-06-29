package com.alten.shop.alterShop.mapper;

import com.alten.shop.alterShop.dto.ProductDto;
import com.alten.shop.alterShop.model.Product;

public class ProductMapper {

	public static Product toEntity(ProductDto productDto) {
		Product product = new Product();
		product.setId(productDto.getId());
		product.setCode(productDto.getCode());
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		product.setQuantity(productDto.getQuantity());
		product.setInventoryStatus(productDto.getInventoryStatus());
		product.setCategory(productDto.getCategory());
		product.setImage(productDto.getImage());
		product.setRating(productDto.getRating());
		return product;
	}

	public static ProductDto toDto(Product product) {
		ProductDto productDto = new ProductDto();
		productDto.setId(product.getId());
		productDto.setCode(product.getCode());
		productDto.setName(product.getName());
		productDto.setDescription(product.getDescription());
		productDto.setPrice(product.getPrice());
		productDto.setQuantity(product.getQuantity());
		productDto.setInventoryStatus(product.getInventoryStatus());
		productDto.setCategory(product.getCategory());
		productDto.setImage(product.getImage());
		productDto.setRating(product.getRating());
		return productDto;
	}
}
