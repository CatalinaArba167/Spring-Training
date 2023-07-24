package ro.msg.learning.shop.Exception;

import java.util.UUID;

public class ProductCategoryAlreadyExistingException extends RuntimeException {
    public ProductCategoryAlreadyExistingException(String name) {
        super("The product category with name: " + name + " already exists!");
    }

    public ProductCategoryAlreadyExistingException(UUID id) {
        super("The product category with id: " + id + " already exists!");
    }
}
