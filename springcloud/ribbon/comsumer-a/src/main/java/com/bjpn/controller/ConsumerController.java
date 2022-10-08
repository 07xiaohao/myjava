package com.bjpn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    LoadBalancerClient loadBalancerClient;
    @GetMapping("/testDemo")
    public String demo(String serviceName){
        String forObject = restTemplate.getForObject ("http://" + serviceName + "/hello", String.class);
            System.out.println (forObject);

        return forObject;
    }
    @GetMapping("/test")
    public String demo1(String serviceName){
        ServiceInstance choose = loadBalancerClient.choose (serviceName);
        return choose.toString ();
    }
}
