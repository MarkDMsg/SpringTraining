package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.CustomerDto;
import ro.msg.learning.shop.dto.LocationDto;
import ro.msg.learning.shop.service.CustomerService;
import ro.msg.learning.shop.service.LocationService;

import java.util.List;

@RequestMapping("/customers")
@RestController

public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto){
        CustomerDto returnedCustomerDto=customerService.createCustomer(customerDto);
        return  new ResponseEntity<>(returnedCustomerDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){
        List<CustomerDto>  returnedCustomers=customerService.getAllCustomerDto();
        return new ResponseEntity<>(returnedCustomers,HttpStatus.OK);
    }
}
