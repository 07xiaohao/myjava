package com.bjpn.controller;

import com.bjpn.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @GetMapping("customer")
    public String toRent(){
        System.out.println ("去租车喽");
        return customerService.rent ();
    }
}
