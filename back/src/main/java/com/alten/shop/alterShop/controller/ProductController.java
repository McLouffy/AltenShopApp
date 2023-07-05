package com.alten.shop.alterShop.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.alten.shop.alterShop.dto.ProductDto;
import com.alten.shop.alterShop.enumeration.CodeRetourRest;
import com.alten.shop.alterShop.exception.ProductNotFoundException;
import com.alten.shop.alterShop.payload.ProductResponse;
import com.alten.shop.alterShop.payload.ReturnMessageDto;
import com.alten.shop.alterShop.service.ProductService;
import com.alten.shop.alterShop.utils.ProductValidationUtils;

/**
 * Contrôleur REST pour la gestion des produits.
 */
@RestController
@RequestMapping("/api/alten")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductValidationUtils productValidationUtils;

	/**
	 * Récupère tous les produits.
	 *
	 * @return Une ResponseEntity contenant la liste des produits et le code de
	 *         statut HTTP OK.
	 */
	@GetMapping("/products")
	public ResponseEntity<List<ProductDto>> getAllProducts() {
		List<ProductDto> products = productService.getAllProducts();

		if (products.isEmpty()) {
			String message = "Aucun produit n'est présent en BDD";
			logger.error(message); // Affiche le message dans la console avec le niveau INFO
		}
		return ResponseEntity.ok(products);
	}

	/**
	 * Récupère un produit par son ID.
	 *
	 * @param id L'ID du produit à récupérer.
	 * @return Une ResponseEntity contenant le produit correspondant à l'ID spécifié
	 *         et le code de statut HTTP OK, ou le code de statut HTTP NOT FOUND si
	 *         aucun produit n'est trouvé.
	 */
	@GetMapping("/product/{id}")
	public ResponseEntity<?> getProductById(@PathVariable Long id) {
		try {
			ProductDto product = productService.getProductById(id);
			return ResponseEntity.ok(product);
		} catch (ProductNotFoundException ex) {
			String message = "Aucun produit avec l'ID " + id + " n'existe en base de données";
			logger.info(message);
			return new ResponseEntity<ReturnMessageDto>(
					new ReturnMessageDto(CodeRetourRest.PRODUCT_NOT_FOUND.getCode(),
							CodeRetourRest.PRODUCT_NOT_FOUND.getDescription(), HttpStatus.NOT_FOUND),
					HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Crée un nouveau produit.
	 *
	 * @param productDto Le DTO du produit à créer.
	 * @return Une ResponseEntity contenant le produit créé et le code de statut
	 *         HTTP CREATED.
	 */
	@PostMapping("/products")
	public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto) {
		// Validation du produit
		ProductResponse response = productValidationUtils.validate(productDto);

		// Vérifier si des erreurs de validation sont présentes
		if (response != null) {
			return new ResponseEntity<ReturnMessageDto>(
					new ReturnMessageDto(CodeRetourRest.ERROR_VALIDATION.getCode(),
							CodeRetourRest.ERROR_VALIDATION.getDescription(), HttpStatus.BAD_REQUEST),
					HttpStatus.BAD_REQUEST);
		}
		// Si aucune erreur de validation, continuer avec la création du produit
		ProductDto createdProduct = productService.createProduct(productDto);
		String message = "Produit crée avec succès";
		logger.info(message);
		return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
	}

	/**
	 * Met à jour un produit.
	 *
	 * @param id         L'ID du produit à mettre à jour.
	 * @param productDto Le DTO du produit contenant les nouvelles données.
	 * @return Une ResponseEntity contenant le produit mis à jour et le code de
	 *         statut HTTP OK, ou le code de statut HTTP NOT FOUND si aucun produit
	 *         n'est trouvé.
	 */
	@PutMapping("/product/{id}")
	public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
		try {
			ProductDto updatedProduct = productService.updateProduct(id, productDto);
			String message = "Mise à jour du produit réussie avec l'id " + id;
			logger.info(message); // Affiche le message dans la console avec le niveau INFO
			return ResponseEntity.ok(updatedProduct);
		} catch (ProductNotFoundException e) {

			return ResponseEntity.notFound().build();
		}
	}
	
	
	@PatchMapping("/product/{id}")
	public ResponseEntity<ProductDto> UpdateOneFieldProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
	    ProductDto updatedProduct = productService.updateOneFieldInProduct(productDto, id);
	    return ResponseEntity.ok(updatedProduct);
	}

	/**
	 * Supprime un produit.
	 *
	 * @param id L'ID du produit à supprimer.
	 * @return Une ResponseEntity avec le code de statut HTTP NO CONTENT si le
	 *         produit est supprimé avec succès, ou le code de statut HTTP NOT FOUND
	 *         si aucun produit n'est trouvé.
	 */
	@DeleteMapping("/product/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
		try {
			productService.deleteProduct(id);
			String message = "Suppression du produit réussie avec l'id " + id;
			logger.info(message); // Affiche le message dans la console avec le niveau INFO
			return ResponseEntity.noContent().build();
		} catch (ProductNotFoundException e) {
			String errorMessage = "Produit non trouvé avec l'ID : " + id;
			logger.error(errorMessage); // Affiche le message d'erreur dans la console avec le niveau ERROR
			return new ResponseEntity<ReturnMessageDto>(
					new ReturnMessageDto(CodeRetourRest.PRODUCT_NOT_FOUND.getCode(),
							CodeRetourRest.PRODUCT_NOT_FOUND.getDescription(), HttpStatus.NOT_FOUND),
					HttpStatus.NOT_FOUND);
		}
	}
}
