package com.example.bookgrim.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthServiceTest {

    @Autowired
    AuthService authService;

    /*@BeforeEach에서 user jwt에 관한 정보 담기*/

//    @Retention(RetentionPolicy.RUNTIME)
//    @WithSecurityContext(factory = WithAuthUserSecurityContextFactory.class)
//    public @interface WithAuthUser{
//        String email();
//        Stirng role();
//    }

}
