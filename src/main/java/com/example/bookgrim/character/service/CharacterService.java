package com.example.bookgrim.character.service;

import com.example.bookgrim.character.domain.Charac;
import com.example.bookgrim.character.dto.NewCharactersRequestDto;
import com.example.bookgrim.character.dto.NewCharactersResponseDto;
import com.example.bookgrim.character.repository.JpaCharacterRepository;
import com.example.bookgrim.common.exception.BadRequestException;
import com.example.bookgrim.common.exception.ErrorCode;
import com.example.bookgrim.common.exception.NotFoundException;
import com.example.bookgrim.story.domain.Story;
import com.example.bookgrim.user.domain.User;
import com.example.bookgrim.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static com.example.bookgrim.common.exception.ErrorCode.NOT_FOUND_USER;

@AllArgsConstructor
@Service
public class CharacterService {
    private JpaCharacterRepository jpaCharacterRepository;
    private UserService userService;
    public NewCharactersResponseDto makeNewCharacter(UserDetails userDetails, NewCharactersRequestDto newCharactersRequestDto, List<MultipartFile> files) {
        List<String> characterList = newCharactersRequestDto.getCharacterList();
        String title = newCharactersRequestDto.getTitle();

        User user = userService.getUserByEmail(userDetails.getUsername()).orElseThrow(() -> new NotFoundException(NOT_FOUND_USER, "로그인 유저 정보가 없습니다"));

        //TODO: characterList, files 길이 동일한지 확인
        if(characterList.toArray().length != files.toArray().length){
            throw new BadRequestException(ErrorCode.CHARACTER_LEN, "캐릭터 정보가 정확하지 않습니다.");
        }

        //TODO: Story DB에 생성요청
        Story newStory = Story.create(title, user);
        //TODO: Character DB에 생성요청 + 이미지 AI로 전달
        //AI로 API 요청 -> img url 반환
        List<String> characterImages = new ArrayList<>();
        //TODO: S3 서버에 저장된 이미지를 반환 (시간이 오래걸릴 듯)
        characterImages.add("1");
        characterImages.add("2");
        for(String characterName : characterList){
            Charac character = Charac.create(newStory, characterName, "imgUrl");

        }

        return new NewCharactersResponseDto(characterImages);
    }
}
