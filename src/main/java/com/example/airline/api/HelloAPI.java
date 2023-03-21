package com.example.airline.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloAPI {

    @GetMapping("api/v1/hello")
    public String hello() {
        return "hello";
    }

}
