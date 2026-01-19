package com.lucas.petshop.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class TestController {

    @PostMapping("/ping")
    public String ping() {
        return "OK";
    }
}

