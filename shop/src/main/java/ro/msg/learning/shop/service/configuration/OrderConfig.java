package ro.msg.learning.shop.service.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.service.ProductService;
import ro.msg.learning.shop.service.StockService;
import ro.msg.learning.shop.service.strategy.LocationStrategy;
import ro.msg.learning.shop.service.strategy.MostAbundantStrategy;
import ro.msg.learning.shop.service.strategy.SingleLocationStrategy;

@RequiredArgsConstructor
@Configuration
public class OrderConfig {

    private enum StrategyType {
        SINGLE_LOCATION,
        MOST_ABUNDANT
    }

    @Value("${location-strategy}")
    private StrategyType configuredStrategy;


    private final StockService stockService;

    private final StockRepository stockRepository;

    private final ProductService productService;

    @Bean
    public LocationStrategy locationStrategy() {
        if (configuredStrategy.equals(StrategyType.SINGLE_LOCATION)) {
            return new SingleLocationStrategy(stockService, productService);
        } else if (configuredStrategy.equals(StrategyType.MOST_ABUNDANT)) {
            return new MostAbundantStrategy(stockService, productService);
        } else
            return null;
    }
}
