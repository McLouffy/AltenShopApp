package com.alten.shop.alterShop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alten.shop.alterShop.exception.ProductNotFoundException;
import com.alten.shop.alterShop.model.Product;
import com.alten.shop.alterShop.repository.ProductRepository;
import com.alten.shop.alterShop.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
    public Product createProduct(Product product) {
        
        return productRepository.save(product);
    }


	 @Override
	    public Product getProductById(Long id) {
	        // Récupérer un produit par son ID
	        return productRepository.findById(id)
	                .orElseThrow(() -> new ProductNotFoundException("Product not found with id : " + id));
	    }


	 @Override
	    public Product updateProduct(Long id, Product updatedProduct) {
	        
		 // Trouver le produit existant ou lancer une exception si non trouvé
	        Product product = productRepository.findById(id)
	                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
	        
	        // Mettre à jour les champs du produit existant avec les valeurs de updatedProduct
	        product.setName(updatedProduct.getName());
	        product.setPrice(updatedProduct.getPrice());
	        

	        // Sauvegarder et renvoyer le produit mis à jour
	        return productRepository.save(product);
	    }

	 @Override
	    public void deleteProduct(Long id) {
	        // Vérifier si le produit existe avant de le supprimer
		 
	        if (!productRepository.existsById(id)) {
	            throw new ProductNotFoundException("Product not found with id: " + id);
	        }
	        productRepository.deleteById(id);
	    }


	 @Override
	    public List<Product> getAllProducts() {
	        // Récupérer tous les produits
	        return productRepository.findAll();
	    }

}
