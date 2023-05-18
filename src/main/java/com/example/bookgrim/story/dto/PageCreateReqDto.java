package com.example.bookgrim.story.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PageCreateReqDto {
    private String content;
    private List<String> characterName;
    private List<String> characterPrompt;
    private String imgUrl;
    private int pageNum;
    private int x;
    private int y;
}
