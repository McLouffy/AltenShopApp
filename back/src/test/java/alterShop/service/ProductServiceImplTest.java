package alterShop.service;

import com.alten.shop.alterShop.exception.ProductNotFoundException;
import com.alten.shop.alterShop.model.Product;
import com.alten.shop.alterShop.repository.ProductRepository;
import com.alten.shop.alterShop.service.impl.ProductServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;



public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateProduct() {
        // Création d'un produit de test
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(10.0);

        // Configuration du comportement du mock
        when(productRepository.save(product)).thenReturn(product);

        // Appel de la méthode à tester
        Product createdProduct = productService.createProduct(product);

        // Vérification que la méthode save du repository a été appelée avec le bon produit
        verify(productRepository).save(product);

        // Vérification que le produit retourné est le même que celui retourné par le repository
        assertEquals(product, createdProduct);
    }
}
