package ro.msg.learning.shop.Exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.UUID;


public class ProductCategoryNotFoundException extends RuntimeException{
    public ProductCategoryNotFoundException(UUID id) {
        super("Could not find product category with id: " + id);
    }
}
