package com.csxkaka.serviceribbon.Hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    /**
     * 开启多个eureka-client实例，分别向服务中心注册，它们仅仅端口号不同，
     * 在浏览器中访问该路径
     * @param name
     * @return
     */
    @GetMapping(value = "/hi")
    public String hiEurekaClient(@RequestParam String name) {
        return helloService.hiEurekaClient(name);
    }

    @GetMapping(value = "/hi/hystrix")
    public String hiHystrix(@RequestParam String name) {
        return helloService.hiHystrix(name);
    }
}
