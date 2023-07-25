package ro.msg.learning.shop.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.DTO.CreateOrderDto;
import ro.msg.learning.shop.Domain.*;
import ro.msg.learning.shop.Mapper.CreateOrderTransferMapper;
import ro.msg.learning.shop.Repository.IOrderDetailRepository;
import ro.msg.learning.shop.Strategy.IStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderDetailService {
    private final IOrderDetailRepository orderDetailRepository;
    private final ProductService productService;
    private final StockService stockService;

    private final IStrategy strategy;

    public List<OrderDetail> createOrderDetails(CreateOrderDto createOrderDto, Orders order) {
        List<OrderDetail> newOrderDetails = new ArrayList<>();
        CreateOrderTransferMapper mapper = new CreateOrderTransferMapper();
        // Create a stream from the orderDetailDtoList and extract the productIds, converting from String to UUID
        List<UUID> productIdsList = mapper.toIdsList(createOrderDto);
        for (UUID productId : productIdsList) {
            //If the product doesn't exist the getProductById function will throw an error
            productService.getProductById(productId);
        }
        List<Integer> quantitiesList = mapper.toQuantityList(createOrderDto);
        Integer noOfProducts = productIdsList.size();

        //The stock form a single location that must be modified
        List<Stock> stockToBeModified = strategy.retrieveSuitableStock(productIdsList, quantitiesList);
        //Modify stock
        List<Stock> modifiedStock = stockService.modifyStock(productIdsList, quantitiesList, stockToBeModified);
        System.out.println(modifiedStock);
        for (int i = 0; i < noOfProducts; i++) {
            //Get the location of the stock
            Location location = modifiedStock.get(i).getLocation();
            //Get the product
            Product product = productService.getProductById(productIdsList.get(i));
            //Create an orderDetail
            OrderDetail orderDetail = OrderDetail.builder()
                    .quantity(quantitiesList.get(i))
                    .orders(order)
                    .product(product)
                    .shippedFrom(location)
                    .build();
            this.orderDetailRepository.save(orderDetail);
            newOrderDetails.add(orderDetail);
        }
        return newOrderDetails;
    }
}
