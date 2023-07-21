package ro.msg.learning.shop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.DTO.LocationSimpleDto;
import ro.msg.learning.shop.DTO.ProductAndProductCategoryDto;
import ro.msg.learning.shop.Service.Implementation.LocationService;
import ro.msg.learning.shop.Service.Implementation.ProductService;

@RequestMapping("/location")
@RestController
@Validated
public class LocationController {
    @Autowired
    private LocationService locationService;

    @PostMapping
    public ResponseEntity<LocationSimpleDto> createLocation(@RequestBody LocationSimpleDto locationSimpleDto) {
        return new ResponseEntity<>(locationService.createLocation(locationSimpleDto), HttpStatus.CREATED);
    }
}
