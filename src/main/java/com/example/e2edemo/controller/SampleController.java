package com.example.e2edemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "sample")
public class SampleController {
    public SampleController() {
        System.out.println("adwsf");
    }

    @GetMapping(path = "hello", produces = "application/json")
    public String getHello() {
        return "hello";
    }
}
