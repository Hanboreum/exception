package com.example.exception.controller;

import com.example.self.model2.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.el.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/c")
public class RestApiCController {

    @GetMapping("")
    public void error() throws ParseException {
        throw new ParseException("parse Exception");

    }

    @ExceptionHandler(value = {ParseException.class})
    public ResponseEntity parseException ( ParseException e){
        log.error("ParseException : {}", e);
        var response = Api.builder()
                .resultMessage(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .resultCode(HttpStatus.URI_TOO_LONG.getReasonPhrase())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body(response);
    }
}
