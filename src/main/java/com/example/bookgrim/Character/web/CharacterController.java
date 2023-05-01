package com.example.bookgrim.Character.web;

import com.amazonaws.services.s3.AmazonS3Client;
import com.example.bookgrim.Character.dto.CharacterCreateReqDto;
import com.example.bookgrim.Character.dto.CharacterResDto;
import com.example.bookgrim.Character.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/character")
public class CharacterController {
    private CharacterService characterService;

    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public void create(
            @RequestPart(value="key") CharacterCreateReqDto characterCreateReqDto,
            @RequestPart(value="mulitpartFile") List<MultipartFile> multipartFile,
            @AuthenticationPrincipal UserDetails userDetails
    ){
        characterService.create(characterCreateReqDto);
    }

}
