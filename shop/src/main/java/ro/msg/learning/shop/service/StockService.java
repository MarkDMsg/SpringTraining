package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.mapper.StockMapper;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StockService {

    private final StockRepository stockRepository;

    private final StockMapper stockMapper;

    private final ProductService productService;

    private final LocationService locationService;

    public List<Location> getLocationsWithSufficientProductsQuantities(List<Pair<Product, Integer>> productsAndQuantities) {
        boolean verifier;
        List<Location> returnedLocations = new ArrayList<>();
        List<Location> allStockLocations = stockRepository.findAll().stream().map(Stock::getLocation).distinct().toList();
        for (Location location : allStockLocations) {
            verifier = true;
            for (Pair<Product, Integer> productAndQuantity : productsAndQuantities) {
                if (!existsProductWithQuantityAtLocation(location, productAndQuantity.a, productAndQuantity.b))
                    verifier = false;
            }
            if (verifier) {
                returnedLocations.add(location);
            }
        }
        return returnedLocations;
    }

    public boolean existsProductWithQuantityAtLocation(Location location, Product product, Integer quantity) {
        List<Stock> stockList = stockRepository.findAll().stream()
                .filter(stock -> stock.getQuantity() >= quantity && stock.getProduct() == product)
                .toList();
        return !stockList.isEmpty();
    }

    /***
     * @return true if quantity substracted successfully, false otherwise
     */
    public boolean substractQuantityFromStock(Product product, Location location, int substractedQuantity) {
        Stock currentStock = stockRepository.findByProductAndLocation(product, location);
        int currentQuantity = currentStock.getQuantity();
        if (currentQuantity >= substractedQuantity) {
            currentQuantity -= substractedQuantity;
            currentStock.setQuantity(currentQuantity);
            stockRepository.save(currentStock);
            return true;
        } else
            return false;
    }


    public void createStock(StockDto stockDto) {
        Location location = locationService.getLocationEntityById(stockDto.getLocationId());
        Product product = productService.getProductEntityById(stockDto.getProductId());
        Stock stock = stockMapper.toStock(stockDto, product, location);
        stockRepository.save(stock);
    }

    public List<StockDto> getAllDtoStocks() {
        return stockRepository.findAll().stream().map(stockMapper::toStockDto).toList();
    }

}
