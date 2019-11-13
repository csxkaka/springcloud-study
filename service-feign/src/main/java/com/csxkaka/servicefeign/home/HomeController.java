package com.csxkaka.servicefeign.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/hi")
    public String sayHiFromEurekaClient(@RequestParam String name) {
        return homeService.sayHiFromEurekaClient(name);
    }

}
