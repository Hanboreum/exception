package com.example.exception.controller;

import com.example.exception.model.Api;
import com.example.exception.model.UserResponse;
import org.apache.catalina.User;
import org.springframework.boot.context.config.ConfigDataException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserApiController {

    //빌더패턴. db대신 유저 정보 객체를 만든다.
    private static List<UserResponse>  userList = List.of(
            UserResponse.builder() //빌더라는 메소드로 시작해 각각의 변수를 지정하고
                    .id("1")
                    .age(12)
                    .name("이이름")
                    .build() //build를 하게 되면 객체 하나가 만들어지는 형태이다.
            ,
            UserResponse.builder()
                    .id("2")
                    .age(32)
                    .name("2user")
                    .build()
    );

    //http://localhost:8080/api/user/id/1
    @GetMapping("id/{userId}") //특정 유저를 찾기 위해 아이디를 받을 것
    //user에 대해 특정 id를 가지는 사용자를 내려준다.
    //user 객체가 아닌 api로 감싸진 user라는 응답을 내릴 것
    public Api<UserResponse> getUser(@PathVariable String userId){ //어노테이션 달고 파라미터는 위 {userId} 와 이름이 같아야 매핑됨
        if(true){ //global excep위한
           throw new RuntimeException("Runtime");
        } //삭제시 정상 작동

        var user = userList.stream().filter(
                //it : 유저 리스트에 들어있는 빌더로 만들어진 객체
                it -> it.getId().equals(userId) //userId와 동일한 것을 받음
        )
                .findFirst()
                .get();//옵셔널한 데이터. 리턴해주기. null일수도 있다.

        Api<UserResponse> response = Api.<UserResponse>builder()
                .resultCode( String.valueOf ( HttpStatus.OK.value()))
                .resultMessage(HttpStatus.OK.name())
                .data(user) //우리가 원하는 유저
                .build(); //근데 이게 없을 수도 있다.

        return response;
    }
}
