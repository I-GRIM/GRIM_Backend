package com.example.bookgrim.user.dto;

import com.example.bookgrim.user.domain.User;
import lombok.Data;

@Data
public class UserResponseDto {
    private String email;
    private String nickname;

    private UserResponseDto(
            String email,
            String nickname
    ){
        this.email = email;
        this.nickname = nickname;
    }

    public static UserResponseDto from(User user){
        return new UserResponseDto(
                user.getNickname(),
                user.getPassword()
        );
    }
}
