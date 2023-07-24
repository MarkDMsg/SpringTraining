package ro.msg.learning.shop.service.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.service.StockService;
import ro.msg.learning.shop.service.strategy.LocationStrategy;
import ro.msg.learning.shop.service.strategy.MostAbundantStrategy;
import ro.msg.learning.shop.service.strategy.SingleLocationStrategy;

import java.util.Objects;

@RequiredArgsConstructor
@Configuration
public class OrderConfig {

    @Value("${locationStrategy}")
    private String locationStrategy;
    
    private final StockService stockService;

    @Bean
    public LocationStrategy locationStrategy() {
        if (Objects.equals(locationStrategy, "singleLocation"))
            return new SingleLocationStrategy(stockService);
        else
            return new MostAbundantStrategy(stockService);

    }
}
