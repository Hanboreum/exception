package com.example.exception.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController {

    //http://localhost:8080/api 실행시 500error (= 시스템 서버 에러)
    @GetMapping(path = "")
    public void hello(){

        throw new RuntimeException("run time exception call");
    }
}
