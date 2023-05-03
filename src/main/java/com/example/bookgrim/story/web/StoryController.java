package com.example.bookgrim.story.web;

import com.example.bookgrim.story.dto.PageCreateReqDto;
import com.example.bookgrim.story.dto.StoryCreateReqDto;
import com.example.bookgrim.story.dto.StoryCreateResDto;
import com.example.bookgrim.story.service.PageService;
import com.example.bookgrim.story.service.StoryService;
import com.example.bookgrim.user.domain.User;
import com.example.bookgrim.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
    public StoryCreateResDto createStory(
            HttpServletRequest response,
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody @Valid StoryCreateReqDto storyCreateReqDto
    ){
//        log.info(authentication.getPrincipal().toString());
//        log.info(storyCreateReqDto.getTitle());
        log.info(userDetails.getUsername());
//        log.info(userDetails.getPassword());
        return this.storyService.createStory(userService.getUserByEmail(userDetails.getUsername()), storyCreateReqDto);
    }

//    @PostMapping("/{storyId}")
//    public PageCreateResDto createPage(
//            HttpServletRequest response,
//            @AuthenticationPrincipal UserDetails userDetails,
//            @PathVariable String storyId,
//            @RequestPart PageCreateReqDto pageCreateReqDto
//    ){
//        return this.pageService.createPage(userDetails.getUsername(), storyId, pageCreateReqDto);
//    }

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

