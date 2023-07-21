package ro.msg.learning.shop.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Keys.StockKey;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.dto.LocationDto;
import ro.msg.learning.shop.dto.StockDto;

@Component
public class StockMapper {
    public StockDto toStockDto(Stock stock){
        return new StockDto(stock.getProduct().getId(),stock.getLocation().getId(),stock.getQuantity());
    }

    public Stock toStock(StockDto stockDto,Product product, Location location){
        return new Stock(StockKey.builder().location(stockDto.getLocationId()).product(stockDto.getProductId()).build(),product,location,stockDto.getQuantity());
    }

}
