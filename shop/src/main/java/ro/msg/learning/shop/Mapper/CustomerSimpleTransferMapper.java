package ro.msg.learning.shop.Mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.DTO.CustomerSimpleDto;
import ro.msg.learning.shop.Domain.Customer;

@Component
public class CustomerSimpleTransferMapper {
    public CustomerSimpleDto toCustomerSimpleDto(Customer customer) {
        CustomerSimpleDto dto = new CustomerSimpleDto();
        dto.setId(customer.getId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setUsername(customer.getUsername());
        dto.setPassword(customer.getPassword());
        dto.setEmailAddress(customer.getEmailAddress());
        return dto;
    }

    public Customer toCustomer(CustomerSimpleDto dto) {
        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setUsername(dto.getUsername());
        customer.setPassword(dto.getPassword());
        customer.setEmailAddress(dto.getEmailAddress());
        return customer;
    }
}
