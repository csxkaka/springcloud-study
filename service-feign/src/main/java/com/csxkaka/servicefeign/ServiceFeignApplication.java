package com.csxkaka.servicefeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * feign 默认集成了ribbon，不开启ribbon项目，开启多个eureka-client项目实例
 * 通过feign消费服务，看是否实现了负载均衡
 */
@EnableFeignClients  // 开启feign
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceFeignApplication.class, args);
    }

}
