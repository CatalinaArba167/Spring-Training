package ro.msg.learning.shop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.DTO.CustomerSimpleDto;
import ro.msg.learning.shop.Service.Implementation.CustomerService;

@RequestMapping("/customer")
@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerSimpleDto> createCustomer(@RequestBody CustomerSimpleDto customerSimpleDto) {
        return new ResponseEntity<>(customerService.createCustomer(customerSimpleDto), HttpStatus.CREATED);
    }
}
