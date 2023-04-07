package com.example.bookgrim.user.web;

import com.example.bookgrim.common.domain.JwtDto;
import com.example.bookgrim.user.dto.SignInRequestDto;
import com.example.bookgrim.user.dto.SignUpRequestDto;
import com.example.bookgrim.user.dto.UserResponseDto;
import com.example.bookgrim.user.service.AuthService;
import com.example.bookgrim.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final AuthService authService;
    /*private final HeaderUtil headerUtil;*/

    @PostMapping("/sign-up")
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserResponseDto signUp(
            HttpServletRequest response,
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody @Valid SignUpRequestDto signUpRequestDto
            ){
        /*this.headerUtil.createHeader(response, "isCompletedSignUp", Boolean.TRUE.toString());*/

        return this.authService.signUp( signUpRequestDto);
    }

    @PostMapping("/sign-in")
    @ResponseStatus(value = HttpStatus.OK)
    public JwtDto signIn(
            HttpServletRequest response,
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody @Valid SignInRequestDto signInRequestDto
    ){
        /*this.headerUtil.createHeader(response, "isCompletedSignUp", Boolean.TRUE.toString());*/
        System.out.println("print");
        return this.authService.signIn( signInRequestDto);
    }
}
