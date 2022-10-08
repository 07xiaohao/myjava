package com.atguigu.springcloud.service;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

public interface PaymentService {

    /**
     * 正常访问，一切OK
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id);

    /**
     * 超时访问，演示降级
     * @param id
     * @return
     */
    public String paymentInfo_TimeOut(Integer id);
    public String paymentCircuitBreaker(Integer id);
}
