package ro.msg.learning.shop.service.strategy;

import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.Pair;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.service.StockService;

import java.util.List;

@RequiredArgsConstructor
public class MostAbundantStrategy implements LocationStrategy {

    private final StockService stockService;

    @Override
    public List<Location> getLocationsByStrategy(List<Pair<Product, Integer>> productsAndQuantities) {
        return stockService.getLocationsMostAbundantQuantityForProduct(productsAndQuantities);
    }
}
