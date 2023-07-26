package ro.msg.learning.shop.Mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.DTO.CustomerSimpleDto;
import ro.msg.learning.shop.Domain.Customer;

@Component
public class CustomerSimpleTransferMapper {
    public static CustomerSimpleDto toDto(Customer customer) {
        return CustomerSimpleDto.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .username(customer.getUsername())
                .password(customer.getPassword())
                .emailAddress(customer.getEmailAddress())
                .build();
    }

    public static Customer toEntity(CustomerSimpleDto dto) {
        return Customer.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .emailAddress(dto.getEmailAddress())
                .build();
    }
}
