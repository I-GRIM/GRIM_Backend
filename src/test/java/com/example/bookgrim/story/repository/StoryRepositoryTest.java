package com.example.bookgrim.story.repository;

import com.example.bookgrim.story.domain.Status;
import com.example.bookgrim.story.domain.Story;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class StoryRepositoryTest {

    @Autowired
    private StoryRepository storyRepository;

    @BeforeEach
    public void insertStory(){
//        for(int i = 1 ; i <=5 ; i++){
//            Story story = Story.of("title"+i,"writer"+im, Status.COMPLETED);
//            storyRepository.save(story);
//        }
    }
//    https://ugo04.tistory.com/97

}