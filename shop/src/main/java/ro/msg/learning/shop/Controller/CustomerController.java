package ro.msg.learning.shop.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.DTO.CustomerSimpleDto;
import ro.msg.learning.shop.Service.CustomerService;

@RequestMapping("/customers")
@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerSimpleDto> create(@RequestBody CustomerSimpleDto customerSimpleDto) {
        return new ResponseEntity<>(customerService.createCustomer(customerSimpleDto), HttpStatus.CREATED);
    }
}
