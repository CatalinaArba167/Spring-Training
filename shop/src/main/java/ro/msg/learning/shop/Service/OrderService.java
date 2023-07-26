package ro.msg.learning.shop.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.DTO.CreateOrderDto;
import ro.msg.learning.shop.Domain.Customer;
import ro.msg.learning.shop.Domain.Orders;
import ro.msg.learning.shop.Exception.NotFoundException;
import ro.msg.learning.shop.Mapper.CreateOrderTransferMapper;
import ro.msg.learning.shop.Repository.IOrderRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    public static final String CUSTOMER_NOT_FOUND = "Customer not found: ";
    private final IOrderRepository orderRepository;
    private final CustomerService customerService;
    private final OrderDetailService orderDetailService;

    public Orders createOrder(CreateOrderDto createOrderDto) {
        //Check if the client exists
        Customer customer = this.customerService.findCustomerById(UUID.fromString(createOrderDto.getCustomerId()));
        if (customer == null) {
            throw new NotFoundException(CUSTOMER_NOT_FOUND + createOrderDto.getCustomerId());
        }
        Orders order = CreateOrderTransferMapper.toEntity(createOrderDto, customer);
        this.orderRepository.save(order);
        this.orderDetailService.createOrderDetails(createOrderDto, order);

        return order;
    }
}
