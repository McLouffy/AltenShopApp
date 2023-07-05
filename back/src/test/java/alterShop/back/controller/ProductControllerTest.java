package alterShop.back.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.alten.shop.alterShop.controller.ProductController;
import com.alten.shop.alterShop.dto.ProductDto;
import com.alten.shop.alterShop.service.ProductService;
import com.alten.shop.alterShop.utils.ProductValidationUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

	@Mock
	private ProductService productService;

	@Mock
	private ProductValidationUtils productValidationUtils;

	@InjectMocks
	private ProductController productController;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void createValidProductTest() {
		// Comportement simulé de productValidationUtils pour validation réussie
		when(productValidationUtils.validate(any(ProductDto.class))).thenReturn(null);

		//Mock pour le comportement de la méthode createProduct() qui retourne l'objet createdProduct
		ProductDto createdProduct = new ProductDto();
		createdProduct.setId(1L);
		createdProduct.setName("Product 1");
		createdProduct.setPrice(10.0);
		when(productService.createProduct(any(ProductDto.class))).thenReturn(createdProduct);

		//Appel de createProduct du productController pour créer un produit 
		//à partir du productDto et récupérer la ResponseEntity correspondante
		ResponseEntity<?> responseEntity = productController.createProduct(new ProductDto());

		 // Vérification des résultats
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertEquals(createdProduct, responseEntity.getBody());
		verify(productValidationUtils, times(1)).validate(any(ProductDto.class));
		verify(productService, times(1)).createProduct(any(ProductDto.class));
	}

}
