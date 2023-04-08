package com.example.bookgrim.user.service;

import com.example.bookgrim.common.domain.JwtDto;
import com.example.bookgrim.common.exception.BadRequestException;
import com.example.bookgrim.common.exception.ErrorCode;
import com.example.bookgrim.config.jwt.JwtTokenProvider;
import com.example.bookgrim.user.domain.Role;
import com.example.bookgrim.user.domain.User;
import com.example.bookgrim.user.dto.SignInRequestDto;
import com.example.bookgrim.user.dto.SignUpRequestDto;
import com.example.bookgrim.user.dto.UserResponseDto;
import com.example.bookgrim.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {


    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public JwtDto signIn(
            SignInRequestDto signInRequestDto
    ) {

        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = signInRequestDto.toAuthentication();
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberId, password);

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        JwtDto jwtTokenDto = jwtTokenProvider.generateTokenDto(authentication);

        // 4. RefreshToken redis에 저장
//        redisTemplate.opsForValue()
//                .set("RT:" + authentication.getName(),
//                        jwtTokenDto.getRefreshToken(),
//                        jwtTokenDto.getRefreshTokenExpiresIn(),
//                        TimeUnit.MILLISECONDS);

        // 5. 토큰 발급
        return jwtTokenDto;
    }

    @Transactional
    public UserResponseDto signUp(
            SignUpRequestDto signUpRequestDto
    ){
        this.userRepository.findByEmail(signUpRequestDto.getEmail()).ifPresent(
                email->{
                    throw new BadRequestException(
                            ErrorCode.DUPLICATE_EMAIL,
                            "중복되는 이메일 입니다."
                    );
                }
        );

        return UserResponseDto.from(userRepository.save(
                User.of(
                        signUpRequestDto.getEmail(),
                        passwordEncoder.encode(signUpRequestDto.getPassword()),
                        signUpRequestDto.getNickname(),
                        Role.USER
                )
        ));
    }
}