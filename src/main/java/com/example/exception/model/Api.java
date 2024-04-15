package com.example.exception.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Api <T>{

    private String resultCode;
    private String resultMessage;
    private T data;
}
