package ro.msg.learning.shop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.DTO.ProductCategorySimpleDto;
import ro.msg.learning.shop.Domain.ProductCategory;
import ro.msg.learning.shop.Service.Implementation.ProductCategoryService;

import java.util.List;
import java.util.UUID;

@RequestMapping("/productCategory")
@RestController
@Validated
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;
    @PostMapping
    public ResponseEntity<ProductCategorySimpleDto> createProductCategory(@RequestBody ProductCategorySimpleDto productCategorySimpleDto) {
        return new ResponseEntity<>(productCategoryService.createProductCategory(productCategorySimpleDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductCategoryById(@PathVariable  UUID  id ) {
        productCategoryService.deleteProductCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping()
    public List<ProductCategorySimpleDto> getAllProductCategories(){
        return productCategoryService.getAllProductCategories();
    }

}
