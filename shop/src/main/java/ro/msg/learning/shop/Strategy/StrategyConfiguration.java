package ro.msg.learning.shop.Strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.Repository.IStockRepository;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class StrategyConfiguration {

    public enum Strategy{
        SINGLE_LOCATION,MOST_ABUNDANT_LOCATION
    }
    @Value("${strategy}")
    private Strategy strategy;

    @Autowired
    private IStockRepository stockRepository;

    @Bean
    public IStrategy selectStrategy()
    {
        if(strategy.equals(Strategy.MOST_ABUNDANT_LOCATION))
        {
            return new MostAbundantLocation(stockRepository);
        }
        else
        {
            return new SingleLocation(stockRepository);
        }
    }

}