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
import io.netty.channel.ChannelOption;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class PageService {

    private PageRepository pageRepository;
    private StoryRepository storyRepository;
    private AwsS3Service awsS3Service;

    public PageResponseDto createPage(
            String storyId,
            PageCreateReqDto pageCreateReqDto,
            MultipartFile background,
            MultipartFile character
    ) throws IOException {

        Story story = storyRepository.findById(storyId).orElseThrow(
                ()->new BadRequestException(
                        ErrorCode.NOT_FOUND_USER,
                        "Story를 찾을 수 없습니다."
                )
        );

        //로컬 위치
        String img_path = "E:\\2023.1\\캡스톤\\GRIM_Backend\\src\\main\\resources\\";
//        String img_path = "/home/ubuntu/cache/";


        File back = new File(img_path+background.getOriginalFilename());
        File charac = new File(img_path+character.getOriginalFilename());

        background.transferTo(back);
        character.transferTo(charac);


        byte[] page_img = createPageIllustration("test",
                img_path + background.getOriginalFilename(),
                img_path + character.getOriginalFilename(),
                pageCreateReqDto.getX(),
                pageCreateReqDto.getY());
        Path paths = Paths.get(img_path + "output.png");
        Files.write(paths, page_img);
        String imgUrl = awsS3Service.uploadImage(page_img, storyId+"_"+pageCreateReqDto.getPageNum()+".png");


        // 이미지가 생성되기도 전에 사용자가 동화 생성을 누르면 DB에 page update 안됨. 추후 수정
        Page page = this.pageRepository.save(
                Page.of(
                        story,
                        pageCreateReqDto.getPageNum(),
                        pageCreateReqDto.getContent(),
                        Status.COMPLETED,
                        imgUrl
                )
        );

        if( story.getTitle_img().equals("0")){
            story.updateTitleImg(imgUrl);
            this.storyRepository.save(story);
        }

        return PageResponseDto.from(page);

    }

    public byte[] createPageIllustration(String prompt, String backpath, String frontpath, int x, int y) throws IOException {
        URI uri = URI.create("http://35.216.5.42:8080/api/createPage");
        log.info(backpath);
        log.info(frontpath);
        // URI 연결하고 AI 서버 내부에서 remove랑 merge하고 byte image반환
        Resource back_img = new FileSystemResource(backpath);
        Resource charac_img = new FileSystemResource(frontpath);
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("prompt",prompt);
        builder.part("x",x);
        builder.part("y",y);
        builder.part("back", back_img).header("Content-Disposition",
                "form-data; name= back; filename=" + back_img.getFilename());
        builder.part("character",charac_img).header("Content-Disposition",
                "form-data; name= character; filename=" + charac_img.getFilename());

        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 300000)
                .responseTimeout(Duration.ofSeconds(300));

        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(clientCodecConfigurer -> clientCodecConfigurer.defaultCodecs().maxInMemorySize(-1))
                .build();

        WebClient client = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .exchangeStrategies(exchangeStrategies)
                .build();

        byte[] response = client.post()
                .uri(uri)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .exchange()
                .block()
                .bodyToMono(byte[].class)
                .doOnError(e -> log.error("Mapping Error : ", e))
                .block();

        System.out.println("완료...");
        System.out.println(response);
        return response;
    }

    public List<PageResponseDto> findByStoryId(String storyId){
        Story story = storyRepository.findById(storyId).orElseThrow(
                ()->new BadRequestException(
                        ErrorCode.NOT_FOUND_USER,
                        "Story를 찾을 수 없습니다."
                )
        );

        List<PageResponseDto> pages = pageRepository.findByStoryId(storyId).
                stream().map(page ->
                        PageResponseDto.from(page))
                .collect(Collectors.toList());

        log.info("pages size"+pages.size());
        return pages;

    }
}