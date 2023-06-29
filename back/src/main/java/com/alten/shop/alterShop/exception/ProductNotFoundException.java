package com.alten.shop.alterShop.exception;


/**
 * Exception levée lorsque le produit n'est pas trouvé.
 */
public class ProductNotFoundException extends RuntimeException {
    
    public ProductNotFoundException(String message) {
        super(message);
    }
    
}