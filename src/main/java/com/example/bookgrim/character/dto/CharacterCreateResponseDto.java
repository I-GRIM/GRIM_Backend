package com.example.bookgrim.character.dto;

import com.example.bookgrim.character.domain.Character;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CharacterCreateResponseDto {
    String userId;
    String name;
    String imgUrl;

    private CharacterCreateResponseDto(
            String userId,
            String name,
            String imgUrl
    ){
        this.userId = userId;
        this.name = name;
        this.imgUrl = imgUrl;
    }
    public static CharacterCreateResponseDto from(
            Character character
    ){
        return new CharacterCreateResponseDto(
                character.getWriter().getId(),
                character.getName(),
                character.getImgUrl()
        );
    }

}
