package com.bjpn.controller;

import com.bjpn.domain.Order;
import com.bjpn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
/**
 * 接口是不能做事情的
 * 如果想做事 必须要有对象
 * 那么这个接口肯定是被创建出代理对象的
 * 动态代理 jdk(java interface 接口 $Proxy )  cglib(subClass 子类)
 * jdk动态代理 只要是代理对象调用的方法必须走 java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
 */
/**
 * 总结
 * 浏览器(前端)-------> user-service(/userDoOrder)-----RPC(feign)--->order-service(/doOrder)
 * feign的默认等待时间时1s
 * 超过1s就在直接报错超时
 *
 * @return
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("userDoOrder")
    public String userDoOrder(){
        System.out.println ("用户进来了");
        String s = userService.doOrder ();
        return s;
    }
    @GetMapping("test")
    public String test(){
        System.out.println (userService.testUrl ("zxh", 18));
        System.out.println (userService.oneParam ("cxk"));
        System.out.println (userService.twoParam ("cgx", 21));
        Order order=Order.builder ().id (1).name ("ydx").price (100.0).time (new Date ()).build ();
        System.out.println (userService.oneObject (order));
        System.out.println (userService.oneObjOneParam (order, "cx"));
        return "ok";
    }


}
