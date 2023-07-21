package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.dto.OrderWithProductsDto;
import ro.msg.learning.shop.dto.ProductWithCategoryDto;
import ro.msg.learning.shop.service.OrderDetailService;

import java.util.UUID;

@RequestMapping("/orders")
@RestController

public class OrderController {

    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping("/{userid}")
    public ResponseEntity<OrderDto> createOrder(@PathVariable("userid") UUID userid,@RequestBody OrderWithProductsDto orderWithProductsDto){
        OrderDto returnedOrder=new OrderDto(orderWithProductsDto.getId(),orderWithProductsDto.getCustomerId(),orderWithProductsDto.getCreatedAt(),orderWithProductsDto.getCountry(),orderWithProductsDto.getCity(),orderWithProductsDto.getCounty(),orderWithProductsDto.getStreetAddress());
        return  new ResponseEntity<>(returnedOrder, HttpStatus.CREATED);
    }

}
