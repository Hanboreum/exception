package com.example.exception.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@Slf4j
@RestControllerAdvice(basePackages = "com.example.exception.controller") // rest api를 사용하는 곳에 예외가 일어나는 것을 감지, 모든 컨트롤러에서 나오는 예외를 잡아줌
//basePackages : 지정된 경로의 예외까지 잡겠다.
public class RestApiExceptionHandler {

    //감지후 어떻게 이 api를 인식할 수 있는지 메소드 작성
    @ExceptionHandler(value ={Exception.class}) //예외를 잡아주는, 어떤 예외를 잡을 것인지 설정
    public ResponseEntity exception( Exception e){
       log.error("RestApiExceptionHandler", e);

       return ResponseEntity.status(200).build();
    }

    //IndexOutOfBoundsException에 대한 예외만 잡음
    @ExceptionHandler(value = { IndexOutOfBoundsException.class })
    public ResponseEntity outOfBound( IndexOutOfBoundsException e){
        log.error("IndexOutOfBoundsException", e);
        return ResponseEntity.status(200).build();
    }
}
// 이 컨트롤러는 두가지 예외를 잡는다. indexoutofbound, exception을 상속받는 모든 예외
// RestControllerAdvice 모든 예외를 잡아주는 글로벌한 예외 컨트롤러이다.