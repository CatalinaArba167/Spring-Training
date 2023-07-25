package ro.msg.learning.shop.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.DTO.LocationSimpleDto;
import ro.msg.learning.shop.Service.LocationService;

@RequestMapping("/locations")
@RestController
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;

    @PostMapping
    public ResponseEntity<LocationSimpleDto> create(@RequestBody LocationSimpleDto locationSimpleDto) {
        return new ResponseEntity<>(locationService.createLocation(locationSimpleDto), HttpStatus.CREATED);
    }
}
