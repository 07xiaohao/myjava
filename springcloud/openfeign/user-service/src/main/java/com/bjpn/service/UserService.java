package com.bjpn.service;

import com.bjpn.domain.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "ORDER-SERVICE")//服务名称
public interface UserService {
    @GetMapping("/doOrder")
    String doOrder();
    @GetMapping("testUrl/{name}/and/{age}")
    public String testUrl(@PathVariable("name")String name, @PathVariable("age")Integer age);
    @GetMapping("oneParam")
    public String oneParam(@RequestParam String name);
    @GetMapping("twoParam")
    public String twoParam(@RequestParam String name,@RequestParam Integer age);
    @PostMapping("oneObject")
    public String oneObject(@RequestBody Order order);
    @PostMapping("oneObjOneParam")
    public String oneObjOneParam(@RequestBody Order order,@RequestParam("name") String name) ;
}
