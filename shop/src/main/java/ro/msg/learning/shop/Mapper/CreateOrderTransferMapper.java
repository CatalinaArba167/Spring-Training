package ro.msg.learning.shop.Mapper;


import org.springframework.stereotype.Component;
import ro.msg.learning.shop.DTO.CreateOrderDto;
import ro.msg.learning.shop.DTO.OrderDetailDto;
import ro.msg.learning.shop.Domain.Customer;
import ro.msg.learning.shop.Domain.OrderDetail;
import ro.msg.learning.shop.Domain.Orders;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CreateOrderTransferMapper {

    public Orders toOrder(CreateOrderDto createOrderDto, Customer customer) {
        Orders order = new Orders();
        String[] addressComponents = createOrderDto.getDeliveryAddress().split(",");
        // Assuming the addressComponents array contains country, city, county, and street address in order:
        order.setCountry(addressComponents[0].trim());
        order.setCity(addressComponents[1].trim());
        order.setCounty(addressComponents[2].trim());
        order.setStreetAddress(addressComponents[3].trim());
        order.setCreatedAt(this.fromStringToTimestamp(createOrderDto.getTimestamp()));
        order.setCustomer(customer);
        return order;
    }

    public List<OrderDetail> toOrderDetail(CreateOrderDto createOrderDto, Orders orders) {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        return null;
    }

    public List<UUID> toIdsList(CreateOrderDto createOrderDto){
        List<UUID> productIdsList = createOrderDto.getOrderDetailDtoList().stream()
                .map(dto -> UUID.fromString(dto.getProductId()))
                .collect(Collectors.toList());
        return productIdsList;
    }

    public List<Integer> toQuantityList(CreateOrderDto createOrderDto){
        List<Integer> quantitiesList = createOrderDto.getOrderDetailDtoList().stream()
                .map(OrderDetailDto::getQuantity)
                .collect(Collectors.toList());
        return quantitiesList;
    }

    private Timestamp fromStringToTimestamp(String string) {
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
