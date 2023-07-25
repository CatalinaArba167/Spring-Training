package ro.msg.learning.shop.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.DTO.ProductCategorySimpleDto;
import ro.msg.learning.shop.Service.ProductCategoryService;

import java.util.List;
import java.util.UUID;

@RequestMapping("/productCategories")
@RestController
@RequiredArgsConstructor
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;

    @PostMapping
    public ResponseEntity<ProductCategorySimpleDto> create(@RequestBody ProductCategorySimpleDto productCategorySimpleDto) {
        return new ResponseEntity<>(productCategoryService.createProductCategory(productCategorySimpleDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        productCategoryService.deleteProductCategory(UUID.fromString(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping()
    public ResponseEntity<List<ProductCategorySimpleDto>> getAll() {
        return new ResponseEntity<>(productCategoryService.getAllProductCategories(), HttpStatus.OK);
    }

}
