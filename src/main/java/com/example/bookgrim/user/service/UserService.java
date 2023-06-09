package com.example.bookgrim.user.service;

import com.example.bookgrim.common.domain.JwtDto;
import com.example.bookgrim.user.domain.Role;
import com.example.bookgrim.user.domain.User;
import com.example.bookgrim.user.dto.SignInRequestDto;
import com.example.bookgrim.user.dto.SignUpRequestDto;
import com.example.bookgrim.user.dto.UserResponseDto;
import com.example.bookgrim.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

}
