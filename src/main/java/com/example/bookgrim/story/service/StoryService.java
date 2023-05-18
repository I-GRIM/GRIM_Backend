package com.example.bookgrim.story.service;

import com.example.bookgrim.character.dto.CharacterResDto;
import com.example.bookgrim.story.domain.Status;
import com.example.bookgrim.story.domain.Story;
import com.example.bookgrim.story.dto.StoryCreateReqDto;
import com.example.bookgrim.story.dto.StoryResponseDto;
import com.example.bookgrim.story.repository.StoryRepository;
import com.example.bookgrim.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                        Status.INCOMPLETED,
                        "0"
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

    public List<StoryResponseDto> findAll(){
        List<StoryResponseDto> storys = storyRepository.findAllDesc().
                stream().map(story ->
                        StoryResponseDto.from(story))
                .collect(Collectors.toList());

        return storys;
    }
}