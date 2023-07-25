package ro.msg.learning.shop.Service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.DTO.CreateOrderDto;
import ro.msg.learning.shop.DTO.OrderDetailDto;
import ro.msg.learning.shop.Domain.*;
import ro.msg.learning.shop.Mapper.CreateOrderTransferMapper;
import ro.msg.learning.shop.Repository.IOrderDetailRepository;
import ro.msg.learning.shop.Strategy.IStrategy;

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

    @Autowired
    private IStrategy strategy;

    public List<OrderDetail> createOrderDetails(CreateOrderDto createOrderDto, Orders order) throws Exception {
        List<OrderDetail> newOrderDetails = new ArrayList<>();
        CreateOrderTransferMapper mapper = null;
        // Create a stream from the orderDetailDtoList and extract the productIds, converting from String to UUID
        List<UUID> productIdsList = mapper.toIdsList(createOrderDto);
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
            OrderDetail orderDetail = new OrderDetail(quantitiesList.get(i), order, product, location);
            this.orderDetailRepository.save(orderDetail);
            newOrderDetails.add(orderDetail);
        }
        return newOrderDetails;
    }
}
