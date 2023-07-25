package ro.msg.learning.shop.service.strategy;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.service.ProductService;
import ro.msg.learning.shop.service.StockService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class MostAbundantStrategy implements LocationStrategy {

    private final StockService stockService;

    private final ProductService productService;

    @Override
    public List<OrderDetail> getOrderDetailsByLocationStrategy(List<OrderDetail> orderDetailList) {
        List<Location> returnedLocations = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetailList) {
            Stock stock = stockService.findStockWithMaximumQuantityOnLocationForProduct(orderDetail.getId().getProductId()).get(0);
            if (stock.getQuantity() < orderDetail.getQuantity()) {
                throw new RuntimeException("insufficient stocks");
            }
            returnedLocations.add(stock.getLocation());
        }
        int index = 0;
        for (OrderDetail orderDetail : orderDetailList) {
            Product product = productService.getProductById(orderDetail.getId().getProductId());
            orderDetail.setShippedFrom(returnedLocations.get(index));
            orderDetail.setProduct(product);
            stockService.substractQuantityFromStock(product, returnedLocations.get(index), orderDetail.getQuantity());
            index++;
        }
        return orderDetailList;
    }
}
