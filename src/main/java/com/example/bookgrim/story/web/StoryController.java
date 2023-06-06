package com.example.bookgrim.story.web;

import com.example.bookgrim.common.exception.BaseRuntimeException;
import com.example.bookgrim.common.exception.ErrorCode;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

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
    @ResponseStatus(value = HttpStatus.CREATED)
    public PageResponseDto createPage(
            HttpServletRequest response,
            @PathVariable String storyId,
            @RequestPart(value="value") PageCreateReqDto pageCreateReqDto,
            @RequestPart(value="background") MultipartFile back,
            @RequestPart(value="character")MultipartFile character
    )  {
        try {
            return this.pageService.createPage(storyId, pageCreateReqDto,back, character);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("")
    public List<StoryResponseDto> findAllStory(
            HttpServletRequest response
    ){
        // 일단은 맛없게 가자
        return this.storyService.findAll();
    }
//
    @GetMapping("/{storyId}")
    public List<PageResponseDto> findPagesByStoryId(
            @PathVariable String storyId
    ){
        return this.pageService.findByStoryId(storyId);
    }
}

