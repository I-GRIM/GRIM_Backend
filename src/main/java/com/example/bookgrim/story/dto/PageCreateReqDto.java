package com.example.bookgrim.story.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PageCreateReqDto {
    private String content;
    private String character;
}
