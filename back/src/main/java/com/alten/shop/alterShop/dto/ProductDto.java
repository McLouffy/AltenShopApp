package com.alten.shop.alterShop.dto;

import lombok.Data;

@Data
public class ProductDto {

    private Long id;
    private String code;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private String inventoryStatus;
    private String category;
    private String image;
    private Double rating;

    
}
