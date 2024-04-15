package com.example.exception.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@Slf4j
@RestController
@RequestMapping("/api/b")
public class RestApiBController {

    //http://localhost:8080/api/b
    @GetMapping(path = "")
    public void hello(){
        throw new NumberFormatException("number format exception");
    }

   @ExceptionHandler(value = { IOException.class}) //핸들러를 이쪽에 달아주게 되면 글로벌로 가지 않고 여기서 해결함
    public ResponseEntity ioException(IOException e){
        log.error("RestApiBController",e);
        return ResponseEntity.ok().build();
    }
}
