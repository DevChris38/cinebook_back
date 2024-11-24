package com.cinebook.cinebookback.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin")
public class AdminDemoController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello world";
    }
}