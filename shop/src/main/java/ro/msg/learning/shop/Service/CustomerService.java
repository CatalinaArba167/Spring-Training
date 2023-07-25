package ro.msg.learning.shop.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.DTO.CustomerSimpleDto;
import ro.msg.learning.shop.Domain.Customer;
import ro.msg.learning.shop.Exception.AlreadyExistsException;
import ro.msg.learning.shop.Exception.NotFoundException;
import ro.msg.learning.shop.Mapper.CustomerSimpleTransferMapper;
import ro.msg.learning.shop.Repository.ICustomerRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {

    public static final String CUSTOMER_WITH_EMAIL_ALREADY_EXISTS = "Customer with email already exists: ";
    public static final String CUSTOMER_WITH_USERNAME_ALREADY_EXISTS = "Customer with username already exists: ";
    public static final String CUSTOMER_NOT_FOUND = "Customer not found: ";
    private final ICustomerRepository customerRepository;

    public Customer findCustomerById(UUID id) {
        Optional<Customer> customer = this.customerRepository.findById(id);
        if (customer.isPresent())
            return customer.get();
        else
            throw new NotFoundException(CUSTOMER_NOT_FOUND + id);
    }

    public CustomerSimpleDto createCustomer(CustomerSimpleDto customerDto) {
        // Check if another customer with the same email or username exists
        if (customerRepository.existsByEmailAddress(customerDto.getEmailAddress())) {
            throw new AlreadyExistsException(CUSTOMER_WITH_EMAIL_ALREADY_EXISTS + customerDto.getEmailAddress());
        }

        if (customerRepository.existsByUsername(customerDto.getUsername())) {
            throw new AlreadyExistsException(CUSTOMER_WITH_USERNAME_ALREADY_EXISTS + customerDto.getUsername());
        }
        // Map the CustomerSimpleDto to Customer entity
        Customer customer = CustomerSimpleTransferMapper.toEntity(customerDto);

        // Save the customer to the database
        Customer savedCustomer = customerRepository.save(customer);

        // Map the saved Customer entity back to CustomerSimpleDto
        return CustomerSimpleTransferMapper.toDto(savedCustomer);
    }
}
