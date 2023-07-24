package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.domain.Address;
import ro.msg.learning.shop.domain.Customer;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.dto.OrderDetailDto;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.dto.OrderWithProductsDto;
import ro.msg.learning.shop.mapper.OrderDetailMapper;
import ro.msg.learning.shop.service.CustomerService;
import ro.msg.learning.shop.service.OrderDetailService;
import ro.msg.learning.shop.service.OrderService;
import ro.msg.learning.shop.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("/orders")
@RestController
@RequiredArgsConstructor
public class OrderController {


    private final OrderDetailService orderDetailService;

    private final OrderService orderService;

    private final CustomerService customerService;

    private final OrderDetailMapper orderDetailMapper;

    private final ProductService productService;

    @PostMapping("/{userid}")
    public ResponseEntity<OrderDto> createOrder(@PathVariable("userid") UUID userid, @RequestBody OrderWithProductsDto orderWithProductsDto) {

        List<Pair<Product, Integer>> productsAndQuantities = new ArrayList<>();
        for (OrderDetailDto orderDetailDto : orderWithProductsDto.getProductsAndQuantities()) {
            OrderDetail orderDetail = orderDetailMapper.toOrderDetail(orderDetailDto);
            Product product = productService.getProductEntityById(orderDetail.getId().getProductId());
            productsAndQuantities.add(new Pair<>(product, orderDetail.getQuantity()));
        }

        Customer customer = customerService.getCustomerAtId(userid);
        if (customer == null)
            throw new RuntimeException();
        Address address = new Address(orderWithProductsDto.getCountry(), orderWithProductsDto.getCity(), orderWithProductsDto.getCounty(), orderWithProductsDto.getStreetAddress());
        OrderDto returnedOrder = orderDetailService.createOrderDetails(customer, productsAndQuantities, address);

        return new ResponseEntity<>(returnedOrder, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> returnedOrders = orderService.getAllDtoOrders();
        return new ResponseEntity<>(returnedOrders, HttpStatus.OK);
    }

}
