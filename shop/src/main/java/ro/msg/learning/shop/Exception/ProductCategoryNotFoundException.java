package ro.msg.learning.shop.Exception;

import java.util.UUID;


public class ProductCategoryNotFoundException extends RuntimeException {
    public ProductCategoryNotFoundException(UUID id) {
        super("Could not find product category with id: " + id);
    }
}
