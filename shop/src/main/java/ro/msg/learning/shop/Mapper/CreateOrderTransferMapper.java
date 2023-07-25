package ro.msg.learning.shop.Mapper;


import org.springframework.stereotype.Component;
import ro.msg.learning.shop.DTO.CreateOrderDto;
import ro.msg.learning.shop.DTO.OrderDetailDto;
import ro.msg.learning.shop.Domain.Customer;
import ro.msg.learning.shop.Domain.Orders;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CreateOrderTransferMapper {

    public static Orders toEntity(CreateOrderDto createOrderDto, Customer customer) {
        String[] addressComponents = createOrderDto.getDeliveryAddress().split(",");
        // Assuming the addressComponents array contains country, city, county, and street address in order:
        return Orders.builder()
                .country(addressComponents[0].trim())
                .city(addressComponents[1].trim())
                .county(addressComponents[2].trim())
                .streetAddress(addressComponents[3].trim())
                .createdAt(CreateOrderTransferMapper.fromStringToTimestamp(createOrderDto.getTimestamp()))
                .customer(customer)
                .build();
    }

    public static List<UUID> toIdsList(CreateOrderDto createOrderDto) {
        List<UUID> productIdsList = createOrderDto.getOrderDetailDtoList().stream()
                .map(dto -> UUID.fromString(dto.getProductId()))
                .collect(Collectors.toList());
        return productIdsList;
    }

    public static List<Integer> toQuantityList(CreateOrderDto createOrderDto) {
        List<Integer> quantitiesList = createOrderDto.getOrderDetailDtoList().stream()
                .map(OrderDetailDto::getQuantity)
                .collect(Collectors.toList());
        return quantitiesList;
    }

    private static Timestamp fromStringToTimestamp(String string) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date parsedDate = dateFormat.parse(string);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            return timestamp;
        } catch (Exception e) { //this generic but you can control another types of exception
            // look the origin of excption
        }
        return null;
    }

}
