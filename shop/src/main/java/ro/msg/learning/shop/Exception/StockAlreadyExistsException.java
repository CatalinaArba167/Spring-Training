package ro.msg.learning.shop.Exception;

public class StockAlreadyExistsException extends RuntimeException {
    public StockAlreadyExistsException(String location, String product) {
        super("The stock with product : " + product + " and location: " + location + " already exists!");
    }
}
