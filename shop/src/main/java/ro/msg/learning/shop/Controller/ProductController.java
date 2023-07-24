package ro.msg.learning.shop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.DTO.ProductAndProductCategoryDto;
import ro.msg.learning.shop.Service.Implementation.ProductService;

import java.util.List;
import java.util.UUID;

@RequestMapping("/product")
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductAndProductCategoryDto> createProduct(@RequestBody ProductAndProductCategoryDto productAndProductCategoryDto) throws Exception {
        return new ResponseEntity<>(productService.createProduct(productAndProductCategoryDto), HttpStatus.CREATED);
    }

    @GetMapping()
    public List<ProductAndProductCategoryDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ProductAndProductCategoryDto getProductById(@PathVariable("id") UUID id) {
        return productService.getProductAndProductCategoryById(id);
    }

    @PutMapping()
    public void updateProduct(@RequestBody ProductAndProductCategoryDto productAndProductCategoryDto) throws Exception {
        productService.updateProduct(productAndProductCategoryDto);
    }

}
