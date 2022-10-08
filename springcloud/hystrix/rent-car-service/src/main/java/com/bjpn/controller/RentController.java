package com.bjpn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RentController {
    @GetMapping("rent")
    public String rent(){
        return "租车成功";
    }
}
