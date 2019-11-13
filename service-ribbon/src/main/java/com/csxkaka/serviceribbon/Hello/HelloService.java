package com.csxkaka.serviceribbon.Hello;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// RestTemplate是基于服务的，Feign是基于接口的
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

    /**
     * 在ribbon中使用断路器Hystrix
     * @HystrixCommand 表面该请求开启了断路器的功能
     * fallbackMethod是请求失败后的回调函数，请求失败后，参数也会一起传给回调函数
     * 注：同一个服务，调用的不可用达到一个阈值(5秒内20次)，则会开启断路器，直接执行回调函数
     * 这样就不会出现请求超时等阻塞现象
     * @param name
     * @return
     */
    @HystrixCommand(fallbackMethod = "hiError")
    public String hiHystrix(String name) {
        return restTemplate.getForObject("http://eureka-client/hi/hystrix/?name=" + name, String.class);
    }

    // 这是一个回调函数，参数即主函数的参数，主函数请求失败后会将参数带过来
    public String hiError(String name) {
        return "hi " + name + ",sorry,error";
    }
}
