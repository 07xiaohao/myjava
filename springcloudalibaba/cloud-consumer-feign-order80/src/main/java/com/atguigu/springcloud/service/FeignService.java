package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface FeignService {




    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) ;

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) ;
    @GetMapping(value = "/payment/discovery")
    public Object discovery();
    @GetMapping(value = "/payment/lb")
    public String getPaymentLB();@GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeOut();


}
