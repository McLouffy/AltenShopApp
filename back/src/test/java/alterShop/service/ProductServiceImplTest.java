package alterShop.service;


import com.alten.shop.alterShop.dto.ProductDto;
import com.alten.shop.alterShop.exception.ProductNotFoundException;
import com.alten.shop.alterShop.mapper.ProductMapper;
import com.alten.shop.alterShop.model.Product;
import com.alten.shop.alterShop.repository.ProductRepository;
import com.alten.shop.alterShop.service.ProductService;
import com.alten.shop.alterShop.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService = new ProductServiceImpl();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateProduct() {
        // Mock des données d'entrée
        ProductDto productDto = new ProductDto();
        productDto.setName("Product 1");
        productDto.setPrice(10.0);

        // Mock du comportement du repository
        Product createdProduct = new Product();
        createdProduct.setId(1L);
        createdProduct.setName("Product 1");
        createdProduct.setPrice(10.0);
        when(productRepository.save(any(Product.class))).thenReturn(createdProduct);

        // Appel de la méthode à tester
        ProductDto productDtoCreated = productService.createProduct(productDto);

        // Vérification des résultats
        assertEquals(1L, productDtoCreated.getId());
        assertEquals("Product 1", productDtoCreated.getName());
        assertEquals(10.0, productDtoCreated.getPrice());
    }
    
    @Test
    public void testDeleteExistingProduct() {
        Long productId = 1L;

        // Configurer le comportement du mock ProductRepository
        when(productRepository.existsById(productId)).thenReturn(true);

        // Exécuter la méthode à tester
        assertDoesNotThrow(() -> productService.deleteProduct(productId));

        // Vérifier que la méthode du repository a été appelée
        verify(productRepository, times(1)).deleteById(productId);
    }


   
}
