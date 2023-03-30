package com.manage.hslibrary.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class HelloController {
    @GetMapping("/")
    public String sayHello() {
        return "index";
    }

    @GetMapping("/hi")
    public String sayHi() {
        return "index";
    }
}
