package com.csxkaka.servicezuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy  // 开启zuul代理
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceZuulApplication {

    /**
     *  根据配置文件中的路由配置：
     *  在浏览器中访问 http://localhost:8099/aip-a/hi?name=csx
     *  则会访问service-ribbon中的hi接口，也就是http://localhost:8093/hi?name=csx
     *
     *  在浏览器中访问 http://localhost:8099/aip-b/hi?name=csx
     *  则会访问service-feign中的hi接口，也就是http://localhost:8094/hi?name=csx
     *
     *  这就实现了zuul路由的功能
     *
     *  service-ribbon 和 service-feign中的两个hi接口，具有负载均衡的功能，
     *  通过zuul后，任然具有负载均衡的功能
     */

    public static void main(String[] args) {
        SpringApplication.run(ServiceZuulApplication.class, args);
    }

}
