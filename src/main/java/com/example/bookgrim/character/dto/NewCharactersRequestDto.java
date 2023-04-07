package com.example.bookgrim.character.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NewCharactersRequestDto {
    private String title;
    private List<String> characterList;
}
