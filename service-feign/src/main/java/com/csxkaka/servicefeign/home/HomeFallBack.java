package com.csxkaka.servicefeign.home;

import org.springframework.stereotype.Component;

@Component
public class HomeFallBack implements HomeService {
    @Override
    public String sayHiFromEurekaClient(String name) {
        return "sorry, from feign, " + name ;
    }
}
