package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Customer;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.dto.CustomerDto;
import ro.msg.learning.shop.dto.LocationDto;
import ro.msg.learning.shop.dto.ProductWithCategoryDto;
import ro.msg.learning.shop.mapper.CustomerMapper;
import ro.msg.learning.shop.repository.CustomerRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerMapper customerMapper;

    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer=customerMapper.toCustomer(customerDto);
        customerRepository.save(customer);
        return customerMapper.toCustomerDto(customer);
    }

    public boolean verifyCustomerExists(UUID id){
        Optional<Customer> customer=customerRepository.findById(id);
        return customer.isPresent();
    }

    public Customer getCustomerAtId(UUID id){
        Optional<Customer> customer=customerRepository.findById(id);
        return customer.orElse(null);
    }

    public List<CustomerDto> getAllCustomerDto(){
        return customerRepository.findAll().stream().map(e->customerMapper.toCustomerDto(e)).toList();
    }
}
