package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.mapper.StockMapper;
import ro.msg.learning.shop.service.LocationService;
import ro.msg.learning.shop.service.ProductService;
import ro.msg.learning.shop.service.StockService;

import java.util.List;

@RequestMapping("/stock")
@RestController
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    private final LocationService locationService;

    private final ProductService productService;

    private final StockMapper stockMapper;

    @PostMapping
    public ResponseEntity<Void> createStock(@RequestBody StockDto stockDto) {
        Location location = locationService.getLocationById(stockDto.getLocationId());
        Product product = productService.getProductById(stockDto.getProductId());
        Stock stock = stockMapper.toStock(stockDto, product, location);
        stockService.createStock(stock);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<StockDto>> getAllStocks() {
        List<StockDto> returnedStocks = stockService.getAllStocks().stream().map(stockMapper::toStockDto).toList();
        return new ResponseEntity<>(returnedStocks, HttpStatus.OK);
    }
}
