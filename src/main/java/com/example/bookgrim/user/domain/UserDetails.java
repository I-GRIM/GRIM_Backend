package com.example.bookgrim.user.domain;

import java.util.ArrayList;

import lombok.Getter;
import org.springframework.security.core.userdetails.User;

@Getter
public class UserDetails extends User{
    com.example.bookgrim.user.domain.User user;

    public UserDetails(com.example.bookgrim.user.domain.User user) {
        super(user.getId(), user.getEmail(), new ArrayList<>());
        this.user = user;
    }
}
