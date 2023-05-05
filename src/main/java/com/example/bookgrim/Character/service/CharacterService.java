package com.example.bookgrim.Character.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.example.bookgrim.Character.domain.Character;
import com.example.bookgrim.Character.dto.CharacterCreateReqDto;
import com.example.bookgrim.Character.dto.CharacterResDto;
import com.example.bookgrim.Character.reposoitory.CharacterRepository;
import com.example.bookgrim.common.SecurityUtil;
import com.example.bookgrim.common.exception.ErrorCode;
import com.example.bookgrim.common.exception.NotFoundException;
import com.example.bookgrim.user.domain.User;
import com.example.bookgrim.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CharacterService {

    private final AmazonS3Client amazonS3Client;
    private CharacterRepository characterRepository;
    private UserRepository userRepository;


    @Transactional
    public void create(CharacterCreateReqDto characterCreateReqDto){
        User user = userRepository.findByEmail(SecurityUtil.getCurrentUserEmail())
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_USER, "로그인 유저 정보가 없습니다."));

        characterRepository.save(
                Character.of(
                        characterCreateReqDto.getUser(),
                        characterCreateReqDto.getCharacter_name(),
                        "url"
                ));
    }
}
