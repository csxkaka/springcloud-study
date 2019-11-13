package com.csxkaka.serviceribbon.Hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 通过RestTemplate来消费服务
     * RestTemplate会自动将eureka-client转化为项目的路径
     * eureka-client就是spring.application.name 实例名称，每个客户端都有一个唯一的名字
     * ribbon就可以通过这个名字找到对应的工程和端口号，也就可以找到具体的接口
     * @param name
     * @return
     */
    public String hiEurekaClient(String name) {
        return restTemplate.getForObject("http://eureka-client/hi?name=" + name, String.class);
    }
}
