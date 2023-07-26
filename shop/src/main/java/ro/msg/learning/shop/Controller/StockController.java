package ro.msg.learning.shop.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.Service.StockService;

@RequestMapping("/stocks")
@RestController
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

//    @PostMapping
//    public ResponseEntity<StockSimpleDto> create(@RequestBody CreateStockDto createStockDto) throws Exception {
//        return new ResponseEntity<>(stockService.createStock(createStockDto), HttpStatus.CREATED);
//    }
}
