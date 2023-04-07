package com.example.bookgrim.character.web;

import com.example.bookgrim.character.dto.NewCharactersImageRequestDto;
import com.example.bookgrim.character.dto.NewCharactersRequestDto;
import com.example.bookgrim.character.dto.NewCharactersResponseDto;
import com.example.bookgrim.character.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/character")
public class CharacterController {
    private final CharacterService characterService;
    /*private final HeaderUtil headerUtil;*/

    @PostMapping("")
    @ResponseStatus(value = HttpStatus.CREATED)
    public NewCharactersResponseDto makeNewCharacters(
            HttpServletRequest response,
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody NewCharactersRequestDto NewCharactersRequestDto,
            @RequestBody NewCharactersImageRequestDto newCharactersImageRequestDto
            ){
        NewCharactersResponseDto newCharactersResponseDto = this.characterService.makeNewCharacter(NewCharactersRequestDto,newCharactersImageRequestDto);

        return newCharactersResponseDto;
    }
}

