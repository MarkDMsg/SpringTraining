package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Customer;
import ro.msg.learning.shop.dto.CustomerDto;
import ro.msg.learning.shop.mapper.CustomerMapper;
import ro.msg.learning.shop.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    public CustomerDto createCustomer(Customer customer) {
        customerRepository.save(customer);
        return customerMapper.toCustomerDto(customer);
    }

    public boolean verifyCustomerExists(UUID id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.isPresent();
    }

    public Customer getCustomerAtId(UUID id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElse(null);
    }

    public List<CustomerDto> getAllCustomerDto() {
        return customerRepository.findAll().stream().map(customerMapper::toCustomerDto).toList();
    }
}
