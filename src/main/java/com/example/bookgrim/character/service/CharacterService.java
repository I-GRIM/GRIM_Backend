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
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

    public CharacterCreateResponseDto createCharacter(
            Optional<User> writer,
            CharacterCreateReqDto charactersRequestDto,
            MultipartFile file
    ) throws IOException {

        //validtion 필요
        String imgUrl = awsS3Service.uploadImage("character",file);

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
