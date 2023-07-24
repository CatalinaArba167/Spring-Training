package ro.msg.learning.shop.Exception;

import java.util.UUID;

public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException(UUID id) {
        super("Could not find location with id: " + id);
    }
}
