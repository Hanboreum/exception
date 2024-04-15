package com.example.exception.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //객체 생성시 빌더 패턴 사용 할 것
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class) //해당 클래스가 snake case로 답변이 내려갈 수 있게
public class Api <T>{

    private String resultCode;
    private String resultMessage;
    private T data;
}
