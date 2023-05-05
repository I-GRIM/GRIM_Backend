package com.example.bookgrim.Character.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterCreateReqDto {
    private String user;
    private String character_name;
}
