package ro.msg.learning.shop.Strategy;

import ro.msg.learning.shop.Domain.Stock;

import java.util.List;
import java.util.UUID;

public interface IStrategy {
    List<Stock> retrieveSuitableStock(List<UUID> products, List<Integer> quantities);
}
