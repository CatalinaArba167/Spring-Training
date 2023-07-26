package ro.msg.learning.shop.Strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.Repository.IStockRepository;

@Configuration
@RequiredArgsConstructor
public class StrategyConfiguration {

    private final IStockRepository stockRepository;
    @Value("${strategy}")
    private Strategy strategy;

    @Bean
    public IStrategy selectStrategy() {
        if (strategy.equals(Strategy.MOST_ABUNDANT_LOCATION)) {
            return new MostAbundantLocation(stockRepository);
        } else {
            return new SingleLocation(stockRepository);
        }
    }

    public enum Strategy {
        SINGLE_LOCATION, MOST_ABUNDANT_LOCATION
    }

}