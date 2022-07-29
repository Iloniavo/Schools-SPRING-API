package com.example.helloworld2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWprldController {
    @GetMapping("/")
    public String hello(){
        return "Hello World!";
    }
}
