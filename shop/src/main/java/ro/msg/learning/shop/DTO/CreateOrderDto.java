package ro.msg.learning.shop.DTO;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderDto {
    private String timestamp;
    private String customerId;
    private String deliveryAddress;
    private List<OrderDetailDto> orderDetailDtoList;
}
