package com.example.exception.exception;

import com.example.exception.controller.RestApiBController;
import com.example.exception.controller.RestApiController;
import com.example.exception.model.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.ParseException;
import java.util.NoSuchElementException;

@Slf4j
//@RestControllerAdvice(basePackages = "com.example.exception.controller") // rest api를 사용하는 곳에 예외가 일어나는 것을 감지, 모든 컨트롤러에서 나오는 예외를 잡아줌
//basePackages : 지정된 경로의 예외까지 잡겠다.
@RestControllerAdvice(basePackageClasses = {RestApiController.class, RestApiBController.class})
//해당 컨트롤러 어드바이스는 해당 클래스에서 발생하는 것 만 잡아주겠다. 배열 설정 가능. 많은 컨트롤러 지정 가능
@Order(1) //globalhandler보다 먼저 처리 되어야하므로 순서 지정
//마이너스 -에 가까울 수록 마지막에 처리 된다.
public class RestApiExceptionHandler {
    @ExceptionHandler(value = { Exception.class}) //예측하지 못한 모든 것 잡아주므로 최상위 클래스 Exception.class
    public ResponseEntity<Api> exception (Exception e){
        log.error("" ,e);

        var response = Api.builder()
                .resultCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .resultMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    //감지후 어떻게 이 api를 인식할 수 있는지 메소드 작성

    //IndexOutOfBoundsException에 대한 예외만 잡음
    @ExceptionHandler(value = { IndexOutOfBoundsException.class })
    public ResponseEntity outOfBound( IndexOutOfBoundsException e){
        log.error("IndexOutOfBoundsException", e);
        return ResponseEntity.status(200).build();
    }
    @ExceptionHandler (value = { ParseException.class})
    public ResponseEntity parseException( ParseException e){
        log.error("parseException :" ,e);
        return ResponseEntity.status(200).build();
    }

    @ExceptionHandler (value={NoSuchElementException.class})
    public ResponseEntity<Api> noSuchElement(NoSuchElementException e ){
        log.error("NoSuchElementException" ,e);
        var response =  Api.builder()
                .resultCode(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .resultMessage(HttpStatus.NOT_FOUND.getReasonPhrase())
                .build() ;

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response); //response entity로 한 번 감싼 api
    }
    //이게 있어서 runtime에러가 안됐다. 모든에러를 잡나보다
   /* @ExceptionHandler(value ={Exception.class}) //예외를 잡아주는, 어떤 예외를 잡을 것인지 설정
    public ResponseEntity exception( Exception e){
       log.error("RestApiExceptionHandler", e);

       return ResponseEntity.status(200).build();
    }*/

}
// 이 컨트롤러는 두가지 예외를 잡는다. indexoutofbound, exception을 상속받는 모든 예외
// RestControllerAdvice 모든 예외를 잡아주는 글로벌한 예외 컨트롤러이다.