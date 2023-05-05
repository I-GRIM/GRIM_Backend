package com.example.bookgrim.story.web;

import com.example.bookgrim.story.dto.PageCreateReqDto;
import com.example.bookgrim.story.dto.PageResponseDto;
import com.example.bookgrim.story.dto.StoryCreateReqDto;
import com.example.bookgrim.story.dto.StoryResponseDto;
import com.example.bookgrim.story.service.PageService;
import com.example.bookgrim.story.service.StoryService;
import com.example.bookgrim.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/story")
public class StoryController {
    private final StoryService storyService;
    private final PageService pageService;
    private final UserService userService;
    /*private final HeaderUtil headerUtil;*/

    @PostMapping("")
    @ResponseStatus(value = HttpStatus.CREATED)
    public StoryResponseDto createStory(
            HttpServletRequest response,
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody @Valid StoryCreateReqDto storyCreateReqDto
    ){
        log.info(userDetails.getUsername());
        return this.storyService.createStory(userService.getUserByEmail(userDetails.getUsername()), storyCreateReqDto);
    }

    @PostMapping("/{storyId}")
    public PageResponseDto createPage(
            HttpServletRequest response,
            @PathVariable String storyId,
            @RequestBody PageCreateReqDto pageCreateReqDto
    ){
        return this.pageService.createPage(storyId, pageCreateReqDto);
    }

//    @GetMapping()
//    public List<StoryResDto> findAllStory(
//
//    ){
//
//    }
//
//    @GetMapping()
//    public StoryResDto findyByStoryId(
//
//    ){
//
//    }
}

