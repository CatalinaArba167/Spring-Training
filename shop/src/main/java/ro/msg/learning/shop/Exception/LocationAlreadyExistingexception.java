package ro.msg.learning.shop.Exception;

import java.util.UUID;

public class LocationAlreadyExistingexception extends RuntimeException {
    public LocationAlreadyExistingexception(UUID id) {
        super("The location with id: " + id + " already exists!");
    }
    public LocationAlreadyExistingexception(String name) {
        super("The location with name: " + name + " already exists!");
    }
}
