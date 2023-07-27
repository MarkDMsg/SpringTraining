package ro.msg.learning.shop.service.strategy;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.service.ProductService;
import ro.msg.learning.shop.service.StockService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class SingleLocationStrategy implements LocationStrategy {

    private final StockService stockService;

    private final ProductService productService;

    @Override
    public List<OrderDetail> getOrderDetailsByLocationStrategy(List<OrderDetail> orderDetailList) {
        boolean verifier;
        List<Location> bestLocations = new ArrayList<>();
        List<Location> allStockLocations = stockService.getAllStockLocations();
        for (Location location : allStockLocations) {
            verifier = true;
            for (OrderDetail orderDetail : orderDetailList) {
                if (!stockService.existsProductWithQuantityAtLocation(location, orderDetail.getId().getProductId(), orderDetail.getQuantity()))
                    verifier = false;
            }
            if (verifier) {
                bestLocations.add(location);
            }
        }
        Location chosenLocation = bestLocations.get(0);
        for (OrderDetail orderDetail : orderDetailList) {
            Product product = productService.getProductById(orderDetail.getId().getProductId());
            orderDetail.setShippedFrom(chosenLocation);
            orderDetail.setProduct(product);
            stockService.substractQuantityFromStock(product, chosenLocation, orderDetail.getQuantity());
        }
        return orderDetailList;
    }
}
