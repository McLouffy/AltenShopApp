package com.alten.shop.alterShop.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.alten.shop.alterShop.dto.ProductDto;
import com.alten.shop.alterShop.payload.ProductResponse;
import com.alten.shop.alterShop.payload.ReturnMessageDto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@Component
public class ProductValidationUtils {

    @Autowired
    private Validator validator;

    //Verification que les contraintes de validation sont respectées
    public ProductResponse validate(ProductDto productDto) {
        ProductResponse productResponse = new ProductResponse();
        List<ReturnMessageDto> listReturnMessageDto = new ArrayList<>();
        String message = "";

        //Si des violations de contrainte sont trouvées -> elles sont capturées dans le Set de violations.
        Set<ConstraintViolation<ProductDto>> violations = validator.validate(productDto);

        
        //parcourt des violations et les ajoute dans une liste de messages d'erreur contenue dans l'objet ProductResponse.
        if (!violations.isEmpty()) {
            for (ConstraintViolation<ProductDto> violation : violations) {
                message = violation.getMessage();
                ReturnMessageDto returnMessageDto = new ReturnMessageDto(HttpStatus.BAD_REQUEST.value(), message);
                listReturnMessageDto.add(returnMessageDto);
            }

            productResponse.setReturnMessageDto(listReturnMessageDto);
            return productResponse;
        }

        return null;
    }
}
