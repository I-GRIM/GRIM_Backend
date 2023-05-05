package com.example.bookgrim.story.service;

import com.example.bookgrim.story.domain.Status;
import com.example.bookgrim.story.domain.Story;
import com.example.bookgrim.story.dto.StoryCreateReqDto;
import com.example.bookgrim.story.dto.StoryResponseDto;
import com.example.bookgrim.story.repository.StoryRepository;
import com.example.bookgrim.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class StoryService {

    private StoryRepository storyRepository;


    public StoryResponseDto createStory(
            Optional<User> user,
            StoryCreateReqDto storyCreateReqDto
    ){
        User writer = user.get();

        Story story = this.storyRepository.save(
                Story.of(
                        storyCreateReqDto.getTitle(),
                        writer,
                        Status.INCOMPLETED
                )
        );
        return StoryResponseDto.from(
                story
        );
    }

    public StoryResponseDto findById(
            String storyId
    ){
        return StoryResponseDto.from(
                this.storyRepository.getReferenceById(storyId)
        );
    }
}