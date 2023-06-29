package com.alten.shop.alterShop.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO représentant un produit.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;

    @NotBlank(message = "Le code du produit est requis")
    @Size(max = 50, message = "Le code du produit ne peut pas dépasser 50 caractères")
    private String code;

    @NotBlank(message = "Le nom du produit est requis")
    @Size(max = 100, message = "Le nom du produit ne peut pas dépasser 100 caractères")
    private String name;

    @Size(max = 500, message = "La description du produit ne peut pas dépasser 500 caractères")
    private String description;

    @NotNull(message = "Le prix du produit est requis")
    @DecimalMin(value = "0.0", inclusive = false, message = "Le prix du produit doit être supérieur à 0")
    private Double price;

    @NotNull(message = "La quantité du produit est requise")
    @Min(value = 0, message = "La quantité du produit ne peut pas être négative")
    private Integer quantity;

    private String inventoryStatus;

    @NotBlank(message = "La catégorie du produit est requise")
    private String category;

    private String image;

    @DecimalMin(value = "0.0", inclusive = false, message = "La rating du produit doit être supérieure à 0")
    @DecimalMax(value = "5.0", inclusive = true, message = "La rating du produit ne peut pas dépasser 5")
    private Double rating;
}
