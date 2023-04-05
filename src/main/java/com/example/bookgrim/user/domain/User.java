package com.example.bookgrim.user.domain;

import com.example.bookgrim.common.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "tb_user")
@NoArgsConstructor
public class User extends BaseEntity {
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false, unique = true)
    private String password;

    @Column(name = "nickname", unique = true)
    private String nickname;

    private User(
            String email,
            String password,
            String nickname
    ){
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public static User of(
            String email,
            String password,
            String nickname
    ){
        return new User(
                email,
                password,
                nickname
        );
    }

    public void signUp(
            String nickname,
            String password
    ){
        this.nickname = nickname;
        this.password = password;
    }
}
