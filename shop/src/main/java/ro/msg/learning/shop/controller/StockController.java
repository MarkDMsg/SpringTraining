package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.LocationDto;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.service.LocationService;
import ro.msg.learning.shop.service.StockService;

import java.util.List;

@RequestMapping("/stock")
@RestController
public class StockController {

    @Autowired
    private StockService stockService;
    @PostMapping
    public ResponseEntity<Void> createLocation(@RequestBody StockDto stockDto){
        stockService.createStock(stockDto);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<StockDto>> getAllStocks(){
        List<StockDto>  returnedStocks=stockService.getAllDtoStocks();
        return new ResponseEntity<>(returnedStocks,HttpStatus.OK);
    }
}
