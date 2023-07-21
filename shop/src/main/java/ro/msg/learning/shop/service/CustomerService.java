package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Customer;
import ro.msg.learning.shop.repository.CustomerRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public boolean verifyCustomerExists(UUID id){
        Optional<Customer> customer=customerRepository.findById(id);
        return customer.isPresent();
    }

}
