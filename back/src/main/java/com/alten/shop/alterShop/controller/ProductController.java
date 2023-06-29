package com.alten.shop.alterShop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alten.shop.alterShop.exception.ProductNotFoundException;
import com.alten.shop.alterShop.model.Product;
import com.alten.shop.alterShop.service.ProductService;


/**
 * Classe de contrôleur REST pour la gestion des produits.
 */
@RestController
@RequestMapping("/api/alten")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Obtenir tous les produits
     *
     * 
     * @return Liste des produits présents en BDD
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    /**
     * Obtenir un produit par ID.
     *
     * @param id L'ID du produit à récupérer.
     * @return Le produit correspondant à l'ID spécifié.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    /**
     * Créer un nouveau produit.
     *
     * @param product Le produit à créer.
     * @return Le produit créé.
     */
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    /**
     * Mettre à jour un produit.
     *
     * @param id du produit à mettre à jour
     * @param product, le produit à mettre à jour
     * @return Le produit créé.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        try {
            Product updatedProduct = productService.updateProduct(id, product);
            return ResponseEntity.ok(updatedProduct);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Suppression d'un produit
     *
     * @param id du produit à supprimer
    
     * 
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (ProductNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
