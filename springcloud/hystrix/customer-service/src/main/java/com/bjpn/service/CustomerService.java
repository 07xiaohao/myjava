package com.bjpn.service;

import com.bjpn.service.impl.CustomerServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "RENT-CAR-SERVICE",fallback = CustomerServiceImpl.class)//服务名称
public interface CustomerService {
    @GetMapping("rent")
    public String rent();
}
