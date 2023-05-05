package com.example.bookgrim.story.service;

import com.example.bookgrim.common.exception.BadRequestException;
import com.example.bookgrim.common.exception.ErrorCode;
import com.example.bookgrim.story.domain.Page;
import com.example.bookgrim.story.domain.Status;
import com.example.bookgrim.story.domain.Story;
import com.example.bookgrim.story.dto.PageCreateReqDto;
import com.example.bookgrim.story.dto.PageResponseDto;
import com.example.bookgrim.story.repository.PageRepository;
import com.example.bookgrim.story.repository.StoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PageService {

    private PageRepository pageRepository;
    private StoryRepository storyRepository;

    public PageResponseDto createPage(
            String storyId,
            PageCreateReqDto pageCreateReqDto
    ){

        Story story = storyRepository.findById(storyId).orElseThrow(
                ()->new BadRequestException(
                        ErrorCode.NOT_FOUND_USER,
                        "Story를 찾을 수 없습니다."
                )
        );

        // AI 서버를 통해 생성된 하나의 이미지
        String fairy_tale = "fairy_tale";


        // 이미지가 생성되기도 전에 사용자가 동화 생성을 누르면 DB에 page update 안됨. 추후 수정
        Page page = this.pageRepository.save(
                Page.of(
                        story,
                        pageCreateReqDto.getPageNum(),
                        pageCreateReqDto.getContent(),
                        Status.COMPLETED,
                        fairy_tale
                )
        );

        return PageResponseDto.from(page);

    }
}
