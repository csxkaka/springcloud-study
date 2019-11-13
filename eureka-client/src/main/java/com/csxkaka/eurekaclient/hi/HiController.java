package com.csxkaka.eurekaclient.hi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

    @Value("${server.port}")
    String port;

    @GetMapping(value = "/hi")
    public String hi(@RequestParam(value = "name", defaultValue = "csxkaka") String name) {
        return "hi " + name + ", i am from " + port;
    }
}
