package com.example.bookgrim.character.dto;

import com.example.bookgrim.character.domain.Character;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterResDto {
    private String userId;
    private String character_name;
    private String url;

    public static CharacterResDto from(
            Character character
    ){
        return new CharacterResDto(
                character.getWriter().getId(),
                character.getName(),
                character.getImgUrl()
        );
    }
}
