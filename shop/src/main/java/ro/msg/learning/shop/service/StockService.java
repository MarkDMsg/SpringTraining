package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.mapper.StockMapper;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class StockService {

    private final StockRepository stockRepository;

    private final StockMapper stockMapper;

    private final ProductService productService;

    private final LocationService locationService;

    public List<Location> getAllStockLocations() {
        return stockRepository.findAll().stream().map(Stock::getLocation).distinct().toList();
    }

    public boolean existsProductWithQuantityAtLocation(Location location, UUID productID, Integer quantity) {
        Product product = productService.getProductEntityById(productID);
        List<Stock> stockList = stockRepository.findAll().stream()
                .filter(stock -> stock.getQuantity() >= quantity && stock.getProduct() == product)
                .toList();
        return !stockList.isEmpty();
    }


    public boolean substractQuantityFromStock(Product product, Location location, int substractedQuantity) {
        Optional<Stock> currentStock = stockRepository.findByProductAndLocation(product.getId(), location.getId());
        if (currentStock.isPresent()) {
            int currentQuantity = currentStock.get().getQuantity();
            if (currentQuantity >= substractedQuantity) {
                currentQuantity -= substractedQuantity;
                currentStock.get().setQuantity(currentQuantity);
                stockRepository.save(currentStock.get());
                return true;
            } else
                throw new RuntimeException("insufficient stocks");
        }
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

    public List<Stock> findStockWithMaximumQuantityOnLocationForProduct(UUID uuid) {
        return stockRepository.findStockWithMaximumQuantityOnLocationForProduct(uuid);
    }
}
