package com.polot.testing.customer;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer-registration")
public class CustomerRegistrationController {

    @PutMapping
    public void registerNewCustomer(
            @RequestBody CustomerRegistrationRequest request) {
    }

    @GetMapping
    public String get(){
        return "Hello";
    }

}
