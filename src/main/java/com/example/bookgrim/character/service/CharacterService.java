package com.example.bookgrim.character.service;

import com.example.bookgrim.character.domain.Character;
import com.example.bookgrim.character.dto.CharacterCreateReqDto;
import com.example.bookgrim.character.dto.CharacterCreateResponseDto;
import com.example.bookgrim.character.dto.CharacterReqDto;
import com.example.bookgrim.character.dto.CharacterResDto;
import com.example.bookgrim.character.repository.CharacterRepository;
import com.example.bookgrim.common.exception.BadRequestException;
import com.example.bookgrim.common.exception.ErrorCode;
import com.example.bookgrim.common.service.AwsS3Service;
import com.example.bookgrim.user.domain.User;
import com.example.bookgrim.user.service.UserService;
import io.netty.channel.ChannelOption;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.netty.http.client.HttpClient;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class CharacterService {
    private CharacterRepository characterRepository;
    private AwsS3Service awsS3Service;
    private UserService userService;

    public byte[] createCharacterIllustration(String prompt, String path) throws IOException {
        URI uri = URI.create("http://35.216.5.42:8080/api");
        Resource img = new FileSystemResource(path);
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("prompt",prompt);
        builder.part("image",img).header("Content-Disposition",
                "form-data; name= image; filename=" + img.getFilename());

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

    public CharacterCreateResponseDto createCharacter(
            Optional<User> writer,
            CharacterCreateReqDto charactersRequestDto,
            MultipartFile file
    ) throws IOException {

        //validtion 필요

        // AI 서버로 요청
        // 먼저 멀티 파트 파일을 디스크에 저장
        log.info("character name :"+file.getOriginalFilename());
        String path = "E:\\2023.1\\캡스톤\\GRIM_Backend\\src\\main\\resources\\" + file.getOriginalFilename();
        File image = new File(path);
        if(!image.exists()){
            image.mkdirs();
        }
        file.transferTo(image);
        // AI 서버로 전달
        String prompt = "ghibli, cute boy";
        byte[] img = createCharacterIllustration(prompt, path);
        Path paths = Paths.get("E:\\2023.1\\캡스톤\\GRIM_Backend\\src\\main\\resources\\charcter_out.png");
        Files.write(paths, img);
        // 결과 이미지 업로드
        String imgUrl = awsS3Service.uploadImage(img, file.getOriginalFilename());
        // 디스크에서 파일 삭제
        //image.delete();
        Character character = this.characterRepository.save(
                Character.of(
                        writer.get(),
                        charactersRequestDto.getName(),
                        imgUrl
                )
        );
        return CharacterCreateResponseDto.from(
                character
                );
    }

    public List<CharacterResDto> findByName(
            CharacterReqDto characterReqDto
    ){
        log.info(characterReqDto.getCharacterName().get(0));
        List<CharacterResDto> characters = characterReqDto.getCharacterName()
                .stream().map(character ->
                        CharacterResDto.from(characterRepository.findByName(character)))
                .collect(Collectors.toList());

        return characters;

    }

//    public NewCharactersResponseDto makeNewCharacter(UserDetails userDetails, NewCharactersRequestDto newCharactersRequestDto, List<MultipartFile> files) {
//        List<String> characterList = newCharactersRequestDto.getCharacterList();
//        String title = newCharactersRequestDto.getTitle();
//
//        User user = userService.getUserByEmail(userDetails.getUsername()).orElseThrow(() -> new NotFoundException(NOT_FOUND_USER, "로그인 유저 정보가 없습니다"));
//
//        //TODO: characterList, files 길이 동일한지 확인
//        if(characterList.toArray().length != files.toArray().length){
//            throw new BadRequestException(ErrorCode.CHARACTER_LEN, "캐릭터 정보가 정확하지 않습니다.");
//        }
//        //TODO: Story DB에 생성요청
//        Story newStory = Story.create(title, user);
//        //TODO: Character DB에 생성요청 + 이미지 AI로 전달
//        //AI로 API 요청 -> img url 반환
//        List<String> characterImages = new ArrayList<>();
//        //TODO: S3 서버에 저장된 이미지를 반환 (시간이 오래걸릴 듯)
//        characterImages.add("1");
//        characterImages.add("2");
//        for(String characterName : characterList){
//            Charac character = Charac.create(newStory, characterName, "imgUrl");
//        }
//
//        return new NewCharactersResponseDto(characterImages);
//    }
}
