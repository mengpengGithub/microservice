package com.captain.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @ClassName ServiceApplication
 * @Description TODO
 * @Author mengpeng
 * @Date 2020/3/15 21:35
 * @Version 1.0
 */
@EnableZuulProxy
@SpringBootApplication
public class ZuulServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulServiceApplication.class, args);
    }

}
