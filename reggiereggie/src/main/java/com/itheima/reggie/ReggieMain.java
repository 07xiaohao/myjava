package com.itheima.reggie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@Slf4j
@ServletComponentScan
public class ReggieMain {
    public static void main(String[] args) {
        log.info ("项目启动");
        SpringApplication.run (ReggieMain.class, args);
    }
}