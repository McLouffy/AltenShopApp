package com.alten.shop.alterShop.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alten.shop.alterShop.exception.ProductNotFoundException;
import com.alten.shop.alterShop.mapper.ProductMapper;
import com.alten.shop.alterShop.model.Product;
import com.alten.shop.alterShop.dto.ProductDto;
import com.alten.shop.alterShop.repository.ProductRepository;
import com.alten.shop.alterShop.service.ProductService;


/**
 * Implémentation du service ProductService pour la gestion des produits.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        // Convertir le DTO en entité
        Product product = ProductMapper.toEntity(productDto);
        // Sauvegarder le produit en base de données
        Product createdProduct = productRepository.save(product);
        // Convertir l'entité en DTO et le renvoyer
        return ProductMapper.toDto(createdProduct);
    }

    @Override
    public ProductDto getProductById(Long id) {
        // Rechercher le produit par son ID en base de données
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        // Convertir l'entité en DTO et le renvoyer
        return ProductMapper.toDto(product);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        // Rechercher le produit existant par son ID en base de données
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        
        // Mettre à jour les champs du produit avec les valeurs du DTO
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        // Mettre à jour les autres champs en fonction des besoins
        
        // Sauvegarder le produit mis à jour en base de données
        Product updatedProduct = productRepository.save(product);
        // Convertir l'entité mise à jour en DTO et le renvoyer
        return ProductMapper.toDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        // Vérifier si le produit existe avant de le supprimer
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        // Supprimer le produit de la base de données
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        // Récupérer tous les produits de la base de données
        List<Product> products = productRepository.findAll();
        // Convertir la liste d'entités en une liste de DTO et la renvoyer
        return products.stream()
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }

	@Override
	public ProductDto updateOneFieldInProduct(ProductDto productDto, Long id) {
		
		 // Rechercher le produit existant par son ID en base de données
	    Product product = productRepository.findById(id)
	            .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

	    // Mettre à jour les champs spécifiques du produit avec les valeurs du DTO
	    if (productDto.getName() != null) {
	        product.setName(productDto.getName());
	    }
	    if (productDto.getPrice() != null) {
	        product.setPrice(productDto.getPrice());
	    }
	    
	    if (productDto.getDescription() != null) {
	        product.setDescription(productDto.getDescription());
	    }
	    if (productDto.getQuantity() != null) {
	        product.setQuantity(productDto.getQuantity());
	    }
	    //Ajout d'autres champs ) mettre à jours en fonction des besoins applicatifs
	   
	    
	    // Sauvegarder le produit mis à jour en base de données
	    Product updatedProduct = productRepository.save(product);

	    // Convertir l'entité mise à jour en DTO et le renvoyer
	    return ProductMapper.toDto(updatedProduct);
		
		
	}
    
   
    
    



}
