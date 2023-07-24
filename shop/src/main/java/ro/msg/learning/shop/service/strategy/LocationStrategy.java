package ro.msg.learning.shop.service.strategy;

import org.antlr.v4.runtime.misc.Pair;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Product;

import java.util.List;

public interface LocationStrategy {

    List<Location> getLocationsByStrategy(List<Pair<Product, Integer>> productsAndQuantities);
}
