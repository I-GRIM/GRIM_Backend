package com.example.bookgrim.story.dto;

import com.example.bookgrim.story.domain.Status;
import com.example.bookgrim.story.domain.Story;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StoryResponseDto {
    private String id;
    private String user_id;
    private String title;
    private Status status;


    public static StoryResponseDto from(
            Story story
    ){
        return new StoryResponseDto(
                story.getId(),
                story.getWriter().getId(),
                story.getTitle(),
                story.getStatus()
        );
    }
}
