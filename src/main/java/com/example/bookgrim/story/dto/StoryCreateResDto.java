package com.example.bookgrim.story.dto;

import com.example.bookgrim.story.domain.Story;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StoryCreateResDto {
    private String title;
    private String email;

    private StoryCreateResDto(
            String title
    ){
        this.title = title;
    }
    public static StoryCreateResDto from(
            Story story
    ){
        return new StoryCreateResDto(
                story.getTitle()
        );
    }
}
