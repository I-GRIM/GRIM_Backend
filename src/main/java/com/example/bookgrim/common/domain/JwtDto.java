package com.example.bookgrim.common.domain;

import lombok.Getter;

import java.util.Optional;

@Getter
public class JwtDto {
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpiresIn;
    private Long refreshTokenExpiresIn;

    private JwtDto(
            String grantType,
            String accessToken,
            String refreshToken,
            Long accessTokenExpiresIn,
            Long refreshTokenExpiresIn
    ){
        this.grantType = grantType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessTokenExpiresIn = accessTokenExpiresIn;
        this.refreshTokenExpiresIn = refreshTokenExpiresIn;
    }

    public static JwtDto of(
            String grantType,
            String accessToken,
            String refreshToken,
            Long accessTokenExpiresIn,
            Long refreshTokenExpiresIn
    ) {
        return new JwtDto(
                grantType,
                accessToken,
                refreshToken,
                accessTokenExpiresIn,
                refreshTokenExpiresIn
        );
    }

//    public Optional<Boolean> getIsCompletedSignUp() {
//        return Optional.ofNullable(isCompletedSignUp);
//    }
}
