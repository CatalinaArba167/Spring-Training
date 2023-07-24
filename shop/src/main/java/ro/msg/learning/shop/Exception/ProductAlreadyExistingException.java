package ro.msg.learning.shop.Exception;

import java.util.UUID;

public class ProductAlreadyExistingException extends RuntimeException {
    public ProductAlreadyExistingException(UUID id) {
        super("The product with id: " + id + " already exists!");
    }

    public ProductAlreadyExistingException(String name) {
        super("The product with name: " + name + " already exists!");
    }
}
