package com.alten.shop.alterShop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Le code du produit est requis")
    @Size(max = 50, message = "Le code du produit ne peut pas dépasser 50 caractères")
    @Column(name = "code")
    private String code;

    @NotBlank(message = "Le nom du produit est requis")
    @Size(max = 100, message = "Le nom du produit ne peut pas dépasser 100 caractères")
    @Column(name = "name")
    private String name;

    @Size(max = 500, message = "La description du produit ne peut pas dépasser 500 caractères")
    @Column(name = "description")
    private String description;

    @NotNull(message = "Le prix du produit est requis")
    @DecimalMin(value = "0.0", inclusive = false, message = "Le prix du produit doit être supérieur à 0")
    @Column(name = "price")
    private Double price;

    @NotNull(message = "La quantité du produit est requise")
    @Min(value = 0, message = "La quantité du produit ne peut pas être négative")
    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "inventory_status")
    private String inventoryStatus;

    @NotBlank(message = "La catégorie du produit est requise")
    @Column(name = "category")
    private String category;

    @Column(name = "image")
    private String image;

    @DecimalMin(value = "0.0", inclusive = false, message = "La note du produit doit être supérieure à 0")
    @DecimalMax(value = "5.0", inclusive = true, message = "La note du produit ne peut pas dépasser 5")
    @Column(name = "rating")
    private Double rating;
}
