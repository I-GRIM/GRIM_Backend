package com.example.bookgrim.story.web;

import com.example.bookgrim.story.service.StoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/story")
public class StoryController {
    private final StoryService storyService;
    /*private final HeaderUtil headerUtil;*/

//    @PostMapping()
//    public StoryCreateReqDto create(
//            HttpServletRequest response,
//            @AuthenticationPrincipal UserDetails userDetails,
//            @RequestPart StoryRequestDto storyRequestDto
//    ){
//
//    }
}

