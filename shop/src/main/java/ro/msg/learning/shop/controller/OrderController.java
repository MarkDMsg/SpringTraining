package ro.msg.learning.shop.controller;

import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.dto.LocationDto;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.dto.OrderWithProductsDto;
import ro.msg.learning.shop.service.OrderDetailService;
import ro.msg.learning.shop.service.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("/orders")
@RestController

public class OrderController {

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private OrderService orderService;


    @PostMapping("/{userid}")
    public ResponseEntity<OrderDto> createOrder(@PathVariable("userid") UUID userid,@RequestBody OrderWithProductsDto orderWithProductsDto){

        List<Pair<Product,Integer>> productsAndQuantities = new ArrayList<>();
        List<Integer> quantityList=orderDetailService.getAllQuantitiesFromOrderDto(orderWithProductsDto);
        List<Product> productList=orderDetailService.getAllProductsFromOrderDto(orderWithProductsDto);
        for(int i=0;i<productList.size();i++){
            productsAndQuantities.add(new Pair<>(productList.get(i),quantityList.get(i)));
        }
        OrderDto returnedOrder=orderDetailService.createOrderDetails(userid,productsAndQuantities,orderWithProductsDto.getCountry(),orderWithProductsDto.getCity(),orderWithProductsDto.getCounty(),orderWithProductsDto.getStreetAddress());

        return  new ResponseEntity<>(returnedOrder, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        List<OrderDto> returnedOrders=orderService.getAllDtoOrders();
        return new ResponseEntity<>(returnedOrders,HttpStatus.OK);
    }

}
