package ro.msg.learning.shop.Service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.DTO.CustomerSimpleDto;
import ro.msg.learning.shop.Domain.Customer;
import ro.msg.learning.shop.Exception.CustomerAlreadyExistsException;
import ro.msg.learning.shop.Mapper.CustomerSimpleTransferMapper;
import ro.msg.learning.shop.Repository.ICustomerRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {
    @Autowired
    private ICustomerRepository customerRepository;

    public Customer findCustomerById(UUID id) throws Exception {
        Optional<Customer> customer = this.customerRepository.findById(id);
        if (customer.isPresent())
            return customer.get();
        else
            throw new Exception("Could not find customer!");
    }

    public CustomerSimpleDto createCustomer(CustomerSimpleDto customerDto) {
        // Check if another customer with the same email or username exists
        if (customerRepository.existsByEmailAddress(customerDto.getEmailAddress())) {
            throw new CustomerAlreadyExistsException("Customer with the same email already exists.");
        }

        if (customerRepository.existsByUsername(customerDto.getUsername())) {
            throw new CustomerAlreadyExistsException("Customer with the same username already exists.");
        }
        // Map the CustomerSimpleDto to Customer entity
        CustomerSimpleTransferMapper mapper = new CustomerSimpleTransferMapper();
        Customer customer = mapper.toCustomer(customerDto);

        // Save the customer to the database
        Customer savedCustomer = customerRepository.save(customer);

        // Map the saved Customer entity back to CustomerSimpleDto
        CustomerSimpleDto savedCustomerDto = mapper.toCustomerSimpleDto(savedCustomer);

        return savedCustomerDto;
    }
}
