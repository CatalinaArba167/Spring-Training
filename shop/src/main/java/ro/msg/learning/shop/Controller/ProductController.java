package ro.msg.learning.shop.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.DTO.ProductAndProductCategoryDto;
import ro.msg.learning.shop.Service.ProductService;

import java.util.List;
import java.util.UUID;

@RequestMapping("/products")
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductAndProductCategoryDto> create(@RequestBody ProductAndProductCategoryDto productAndProductCategoryDto) {
        return new ResponseEntity<>(productService.createProduct(productAndProductCategoryDto), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<ProductAndProductCategoryDto>> getAll() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductAndProductCategoryDto> getById(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(productService.getProductAndProductCategoryById(id), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<ProductAndProductCategoryDto> update(@RequestBody ProductAndProductCategoryDto productAndProductCategoryDto) throws Exception {
        return new ResponseEntity<>(productService.updateProduct(productAndProductCategoryDto), HttpStatus.CREATED);
    }

}
