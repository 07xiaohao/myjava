package com.bjpn;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class ComsumerAApplication {

    public static void main(String[] args) {
        SpringApplication.run (ComsumerAApplication.class, args);
    }
@Bean
@LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate ();
}
    /**
     * 往容器中放一个rule对象
     * 你访问任何一个提供者 都是这个算法
     * @return
     */
    @Bean
    public IRule myRule(){
        return new RandomRule ();
    }

}
