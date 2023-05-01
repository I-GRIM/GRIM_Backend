package com.example.bookgrim.user.web;

import com.example.bookgrim.user.dto.SignInRequestDto;
import com.example.bookgrim.user.service.AuthService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@MockBean(JpaMetamodelMappingContext.class)
class AuthControllerTest {
    private MockMvc mockMvc;

    @Mock
    private AuthService authService;

    @Test
    public void successSignIn() throws Exception{
        //given
        SignInRequestDto signInRequestDto = new SignInRequestDto(
                "email",
                "nickname",
                "password");
        //when

        //then
    }
}