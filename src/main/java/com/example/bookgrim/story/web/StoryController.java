package com.example.bookgrim.story.web;

import com.example.bookgrim.character.dto.NewCharactersRequestDto;
import com.example.bookgrim.character.dto.NewCharactersResponseDto;
import com.example.bookgrim.character.service.CharacterService;
import com.example.bookgrim.story.service.StoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/character")
public class StoryController {
    private final StoryService storyService;
    /*private final HeaderUtil headerUtil;*/


}

