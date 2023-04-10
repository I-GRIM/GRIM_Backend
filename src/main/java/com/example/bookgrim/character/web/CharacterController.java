package com.example.bookgrim.character.web;

import com.example.bookgrim.character.dto.NewCharactersImageRequestDto;
import com.example.bookgrim.character.dto.NewCharactersRequestDto;
import com.example.bookgrim.character.dto.NewCharactersResponseDto;
import com.example.bookgrim.character.service.CharacterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/character")
public class CharacterController {
    private final CharacterService characterService;
    /*private final HeaderUtil headerUtil;*/

    @PostMapping(value = "",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(value = HttpStatus.CREATED)
    public NewCharactersResponseDto makeNewCharacters(
            HttpServletRequest response,
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestPart NewCharactersRequestDto newCharactersRequestDto,
            @RequestPart List<MultipartFile> files
            ){

        NewCharactersResponseDto newCharactersResponseDto = this.characterService.makeNewCharacter(userDetails, newCharactersRequestDto,files);

        return newCharactersResponseDto;
    }
}

