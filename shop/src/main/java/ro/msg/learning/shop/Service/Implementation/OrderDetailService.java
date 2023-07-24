package ro.msg.learning.shop.Service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.DTO.CreateOrderDto;
import ro.msg.learning.shop.DTO.OrderDetailDto;
import ro.msg.learning.shop.Domain.*;
import ro.msg.learning.shop.Repository.IOrderDetailRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderDetailService {
    @Autowired
    private IOrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductService productService;

    @Autowired
    private StockService stockService;

    public List<OrderDetail> createOrderDetails(CreateOrderDto createOrderDto, Orders order) throws Exception {
        List<OrderDetail> newOrderDetails = new ArrayList<>();
        List<OrderDetailDto> orderDetailDtoList = createOrderDto.getOrderDetailDtoList();
        // Create a stream from the orderDetailDtoList and extract the productIds, converting from String to UUID
        List<UUID> productIdsList = orderDetailDtoList.stream()
                .map(dto -> UUID.fromString(dto.getProductId()))
                .collect(Collectors.toList());
        List<Integer> quantitiesList = createOrderDto.getOrderDetailDtoList().stream()
                .map(OrderDetailDto::getQuantity)
                .collect(Collectors.toList());
        Integer noOfProducts = productIdsList.size();

        //The stock form a single location that must be modified
        List<Stock> stockToBeModified = stockService.getStocksFromASingleLocation(productIdsList, quantitiesList);
        //Modify stock
        List<Stock> modifiedStock = stockService.modifyStock(productIdsList, quantitiesList, stockToBeModified);
        System.out.println(modifiedStock);
        //All stocks have the same location
        Location location = modifiedStock.get(0).getLocation();
        for (int i = 0; i < noOfProducts; i++) {
            //Get the product
            Product product = productService.getProductById(productIdsList.get(i));
            //Create an orderDetail
            OrderDetail orderDetail = new OrderDetail(quantitiesList.get(i), order, product, location);
            this.orderDetailRepository.save(orderDetail);
            newOrderDetails.add(orderDetail);
        }
        return newOrderDetails;
    }
}
