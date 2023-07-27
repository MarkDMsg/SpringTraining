package ro.msg.learning.shop.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Customer;
import ro.msg.learning.shop.dto.CustomerDto;

@Component
public class CustomerMapper {
    public CustomerDto toCustomerDto(Customer customer) {
        return new CustomerDto(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getUsername(), customer.getPassword(), customer.getEmailAddress());
    }

    public Customer toCustomer(CustomerDto customerDto) {
        return new Customer(customerDto.getFirstName(), customerDto.getLastName(), customerDto.getUsername(), customerDto.getPassword(), customerDto.getEmailAddress());
    }
}
