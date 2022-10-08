package com.bjpn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class OrderController {
    @GetMapping("/doOrder")
    public String doOrder(){

        return "油条豆浆-热干面";
    }
}
