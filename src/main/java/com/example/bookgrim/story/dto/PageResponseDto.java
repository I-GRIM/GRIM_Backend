package com.example.bookgrim.story.dto;

import com.example.bookgrim.story.domain.Page;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PageResponseDto {
    private String storyId;
    private int pageOrder;
    private String imgUrl;
    private String content;

    public PageResponseDto(String storyId, int pageOrder, String imgUrl, String content){
        this.storyId = storyId;
        this.pageOrder =pageOrder;
        this.imgUrl = imgUrl;
        this.content = content;
    }
    public static PageResponseDto from(
            Page page
    ){
        return new PageResponseDto(
                page.getStory().getId(),
                page.getPage_order(),
                page.getPage_image_url(),
                page.getContent()
        );
    }
}
