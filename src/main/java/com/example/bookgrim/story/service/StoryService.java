package com.example.bookgrim.story.service;

import com.example.bookgrim.story.domain.Status;
import com.example.bookgrim.story.domain.Story;
import com.example.bookgrim.story.dto.StoryCreateReqDto;
import com.example.bookgrim.story.dto.StoryCreateResDto;
import com.example.bookgrim.story.repository.StoryRepository;
import com.example.bookgrim.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class StoryService {

    private StoryRepository storyRepository;


    public StoryCreateResDto createStory(
            Optional<User> user,
            StoryCreateReqDto storyCreateReqDto
    ){
        User writer = user.get();
        log.info(storyCreateReqDto.getTitle());
        log.info(writer.getEmail());
        log.info(writer.getId());

        Story story = this.storyRepository.save(
                Story.of(
                        storyCreateReqDto.getTitle(),
                        writer,
                        Status.INCOMPLETED
                )
        );
        return StoryCreateResDto.from(
                story
        );
    }
}