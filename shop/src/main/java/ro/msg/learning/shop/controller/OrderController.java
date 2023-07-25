package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.domain.Customer;
import ro.msg.learning.shop.domain.ProductOrder;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.mapper.OrderDetailMapper;
import ro.msg.learning.shop.mapper.OrderMapper;
import ro.msg.learning.shop.service.CustomerService;
import ro.msg.learning.shop.service.OrderDetailService;
import ro.msg.learning.shop.service.OrderService;
import ro.msg.learning.shop.service.ProductService;

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

    private final OrderMapper orderMapper;

    @PostMapping("/{userid}")
    public ResponseEntity<?> createOrder(@PathVariable("userid") UUID userid, @RequestBody OrderDto orderDto) {
        Customer customer = customerService.getCustomerAtId(userid);
        if (customer == null)
            throw new RuntimeException();
        try {
            ProductOrder productOrder = orderMapper.toOrder(orderDto);
            productOrder.setCustomer(customer);
            OrderDto returnedOrder = orderService.makeOrder(productOrder);
            return ResponseEntity.status(HttpStatus.CREATED).body(returnedOrder);

        } catch (RuntimeException re) {
            if (re.getMessage().equals("insufficient stocks"))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(re.getMessage());
        }
        return null;
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> returnedOrders = orderService.getAllDtoOrders();
        return new ResponseEntity<>(returnedOrders, HttpStatus.OK);
    }

}
