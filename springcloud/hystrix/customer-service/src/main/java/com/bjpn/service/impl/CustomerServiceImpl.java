package com.bjpn.service.impl;

import com.bjpn.service.CustomerService;
import org.springframework.stereotype.Component;

@Component
public class CustomerServiceImpl implements CustomerService {
    @Override
    public String rent() {
        return "我是备选";
    }
}
