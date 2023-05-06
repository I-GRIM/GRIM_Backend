package com.example.bookgrim.user.domain;

import com.example.bookgrim.common.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

@Slf4j
@Entity
@Getter
@Table(name = "tb_user")
@NoArgsConstructor
public class User extends BaseEntity{
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false, unique = true)
    private String password;

    @Column(name = "nickname", unique = true)
    private String nickname;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    private User(
            String email,
            String password,
            String nickname,
            Role role
    ){
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
    }

    public static User of(
            String email,
            String password,
            String nickname,
            Role role
    ){
        return new User(
                email,
                password,
                nickname,
                role
        );
    }

    public void signUp(
            String email,
            String nickname,
            String password,
            Role role
    ){
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.role = role;
    }

    public boolean matchPassword(String password){
        log.info(password);
        log.info(this.getPassword());
        if(this.password.equals(password)){
            return true;
        }
        else return false;
    }
}
