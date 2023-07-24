package ro.msg.learning.shop.Service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.DTO.CreateOrderDto;
import ro.msg.learning.shop.Domain.Customer;
import ro.msg.learning.shop.Domain.Orders;
import ro.msg.learning.shop.Mapper.CreateOrderTransferMapper;
import ro.msg.learning.shop.Repository.IOrderRepository;

import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderDetailService orderDetailService;

    public Orders createOrder(CreateOrderDto createOrderDto) throws Exception {
        //Check if the client exists
        Customer customer = this.customerService.findCustomerById(UUID.fromString(createOrderDto.getCustomerId()));
        if (customer == null) {
            throw new Exception("Invalid customer when creating an order!");
        }
        CreateOrderTransferMapper mapper = new CreateOrderTransferMapper();
        Orders order = mapper.toOrder(createOrderDto, customer);
        this.orderRepository.save(order);
        this.orderDetailService.createOrderDetails(createOrderDto, order);

        return order;
    }
}
