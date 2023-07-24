package ro.msg.learning.shop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.DTO.CreateOrderDto;
import ro.msg.learning.shop.Domain.Orders;
import ro.msg.learning.shop.Service.Implementation.OrderService;

@RequestMapping("/order")
@RestController
public class OrdersController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Orders> createOrder(@RequestBody CreateOrderDto createOrderDto) {
        try {
            return new ResponseEntity<>(orderService.createOrder(createOrderDto), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
