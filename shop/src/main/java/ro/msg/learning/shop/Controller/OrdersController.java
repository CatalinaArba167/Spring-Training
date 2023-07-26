package ro.msg.learning.shop.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.DTO.CreateOrderDto;
import ro.msg.learning.shop.Domain.Orders;
import ro.msg.learning.shop.Service.OrderService;

@RequestMapping("/orders")
@RestController
@RequiredArgsConstructor
public class OrdersController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Orders> create(@RequestBody CreateOrderDto createOrderDto) {
        return new ResponseEntity<>(orderService.createOrder(createOrderDto), HttpStatus.CREATED);

    }
}
