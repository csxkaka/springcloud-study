package com.csxkaka.servicefeign.home;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 这是一个接口
 * feign采用的是基于接口的注解
 * @FeignClient(value="eureka-client") 表示这个接口是访问eureka-client项目中的接口
 */
@FeignClient(value = "eureka-client")
public interface HomeService {

    /**
     * 这里访问的其实就是http://eureka-client:8091/hi?name=csx
     * 如果eureka-client开启了多个实例，那么会实现负载均衡，以轮询的方式访问:
     *   http://eureka-client:8091/hi?name=csx 和 http://eureka-client:8091/hi?name=csx
     *
     * 请求方法必须一样，这里是get方法，eureka-client那边的Controller中必须也是get方法
     * @RequestParam 表名这个name是通过url传给eureka-client的参数
     * eureka-client那边必须也使用@RequestParam 接收参数
     * @param name
     * @return
     */
    @GetMapping(value = "/hi")
    String sayHiFromEurekaClient(@RequestParam("name") String name);
}
