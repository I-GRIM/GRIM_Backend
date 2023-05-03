package com.example.bookgrim.character.web;

import com.example.bookgrim.character.dto.CharacterCreateReqDto;
import com.example.bookgrim.character.dto.CharacterCreateResponseDto;
import com.example.bookgrim.character.service.CharacterService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/character")
public class CharacterController {
    private final CharacterService characterService;
    /*private final HeaderUtil headerUtil;*/

    @PostMapping(value = "",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(value = HttpStatus.CREATED)
    public CharacterCreateResponseDto makeNewCharacters(
            HttpServletRequest response,
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestPart(value="value") CharacterCreateReqDto charactersRequestDto,
            @RequestPart(value="charac") MultipartFile file
            ) throws IOException {

        CharacterCreateResponseDto newCharactersResponseDto = this.characterService.createCharacter(userDetails, charactersRequestDto,file);

        return newCharactersResponseDto;
    }
}

