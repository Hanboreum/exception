package com.example.exception.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api")
public class RestApiController {

    //http://localhost:8080/api 실행시 500error (= 시스템 서버 에러)
    @GetMapping(path = "")
    public void hello(){
        var list = List.of("hello");
        var element = list.get(1);
        log.info("element:  {}", element); //500 error": "Internal Server Error",
    }
}
//throw new RuntimeException("run time exception call");