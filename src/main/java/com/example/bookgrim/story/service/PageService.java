package com.example.bookgrim.story.service;

import com.example.bookgrim.common.exception.BadRequestException;
import com.example.bookgrim.common.exception.ErrorCode;
import com.example.bookgrim.common.service.AwsS3Service;
import com.example.bookgrim.story.domain.Page;
import com.example.bookgrim.story.domain.Status;
import com.example.bookgrim.story.domain.Story;
import com.example.bookgrim.story.dto.PageCreateReqDto;
import com.example.bookgrim.story.dto.PageResponseDto;
import com.example.bookgrim.story.repository.PageRepository;
import com.example.bookgrim.story.repository.StoryRepository;
import lombok.AllArgsConstructor;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PageService {

    private PageRepository pageRepository;
    private StoryRepository storyRepository;
    private AwsS3Service awsS3Service;

    public PageResponseDto createPage(
            String storyId,
            PageCreateReqDto pageCreateReqDto,
            MultipartFile background
    ) throws IOException {

        Story story = storyRepository.findById(storyId).orElseThrow(
                ()->new BadRequestException(
                        ErrorCode.NOT_FOUND_USER,
                        "Story를 찾을 수 없습니다."
                )
        );

        // AI 서버를 통해 image를 전달하면 그냥 받아서 S3에 저장

        // AI 서버를 통해 image_url을 전달받으면 이걸 다시 변환해서 S3에 저장
        //https://amikim5263.tistory.com/21

//        String extension  = test_page_url.substring(test_page_url.indexOf('.') + 1);
//
//        BufferedImage image = ImageIO.read(url);
//        MultiparFile file = new File("1.jpg");


        // Image URL -> Fileitem -> Multipart
        // Multipart back + Multipart character
        String test_image_url = "E:\\2023.1\\캡스톤\\테스트 페이지 그림\\1.jpg";
        URL url = new URL(test_image_url);
//         URL -> FILE -> BurfferdImage
        File file = new File(test_image_url);
        ImageIO.read(url);
//        MultipartFile multipartFile = Mock

//        BufferedImage image = ImageIO.read(file);

//https://hanseokhyeon.tistory.com/entry/JAVA-BufferedImage-to-MultipartFile

        FileItem fileItem = new DiskFileItem("1",Files.probeContentType(file.toPath()),false, file.getName(),(int)file.length(),file.getParentFile());


        // Multipartfile ( back ) , ??? ( name으로 character image를 s3에서 찾앚와 ), PageCreateReq
        // 위의 세 개를 AI쪽에 넘겨서 하나의 image : MultipartFile을 받는다.
        //

        String imgUrl = awsS3Service.uploadImage("test_page",background);


        // 이미지가 생성되기도 전에 사용자가 동화 생성을 누르면 DB에 page update 안됨. 추후 수정
        Page page = this.pageRepository.save(
                Page.of(
                        story,
                        pageCreateReqDto.getPageNum(),
                        pageCreateReqDto.getContent(),
                        Status.COMPLETED,
                        pageCreateReqDto.getImgUrl()
                )
        );

        return PageResponseDto.from(page);

    }
}