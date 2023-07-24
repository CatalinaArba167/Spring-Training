package ro.msg.learning.shop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.Service.Implementation.StockService;

@RequestMapping("/stock")
@RestController
public class StockController {
    @Autowired
    private StockService stockService;

//    @PostMapping
//    public ResponseEntity<StockSimpleDto> create(@RequestBody CreateStockDto createStockDto) throws Exception {
//        return new ResponseEntity<>(stockService.createStock(createStockDto), HttpStatus.CREATED);
//    }
}
