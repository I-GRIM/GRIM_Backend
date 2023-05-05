package com.example.bookgrim.character.dto;

import com.example.bookgrim.character.domain.Character;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CharacterCreateReqDto {
    private String name;

    private CharacterCreateReqDto(
            String name
    ){
        this.name = name;
    }
}
