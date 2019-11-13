package com.csxkaka.serviceribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableHystrix  // 开启熔断器功能
@EnableDiscoveryClient  // 支持多种注册中心，Eureka,Zookeeper,Consul
@EnableEurekaClient     // 只支持Eureka注册中心
@SpringBootApplication
public class ServiceRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceRibbonApplication.class, args);
    }

    /**
     * SpringCloud 有两种服务调用方式，一种是通过ribbon+RestTemplate，另一种是feign
     *
     * feign 默认集成了ribbon
     */

    // 表明这个RestTemplate开启负载均衡功能
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
