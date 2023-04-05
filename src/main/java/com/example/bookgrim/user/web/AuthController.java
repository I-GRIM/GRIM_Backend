package com.example.bookgrim.user.web;

import com.example.bookgrim.user.dto.SignUpRequestDto;
import com.example.bookgrim.user.dto.UserResponseDto;
import com.example.bookgrim.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    /*private final HeaderUtil headerUtil;*/

    @PostMapping("/sign-up")
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserResponseDto signUp(
            HttpServletRequest response,
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody SignUpRequestDto signUpRequestDto
            ){
        UserResponseDto userResponseDto = this.userService.signUp(userDetails.getUser(), signUpRequestDto);
        /*this.headerUtil.createHeader(response, "isCompletedSignUp", Boolean.TRUE.toString());*/

        return userResponseDto;
    }
}
