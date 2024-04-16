package com.example.exception.exception;
//모든 에러를 통과하고 마지막으로 오는 예외처리클래스
import com.example.exception.model.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@Slf4j
@RestControllerAdvice
//@Order(value = Integer.MAX_VALUE) //예외처리 순서 정하기. global은 가장 마지막에 실행.
//Integer.MAX_VALUE 가 가장 마지막 실행이라는 뜻
public class GlobalExceptionHandler {

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
}
