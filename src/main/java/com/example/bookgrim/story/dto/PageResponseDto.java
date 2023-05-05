package com.example.bookgrim.story.dto;

import com.example.bookgrim.story.domain.Page;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PageResponseDto {
    private String storyId;
    private int pageOrder;
    private String imgUrl;

    public static PageResponseDto from(
            Page page
    ){
        return new PageResponseDto(
                page.getStory().getId(),
                page.getPage_order(),
                page.getPage_image_url()
        );
    }
}
