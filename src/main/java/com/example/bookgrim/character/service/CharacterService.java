package com.example.bookgrim.character.service;

import com.example.bookgrim.character.dto.NewCharactersImageRequestDto;
import com.example.bookgrim.character.dto.NewCharactersRequestDto;
import com.example.bookgrim.character.dto.NewCharactersResponseDto;
import com.example.bookgrim.character.repository.JpaCharacterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@Service
public class CharacterService {
    private JpaCharacterRepository jpaCharacterRepository;

    public NewCharactersResponseDto makeNewCharacter(NewCharactersRequestDto newCharactersRequestDto, NewCharactersImageRequestDto newCharactersImageRequestDto){
        List<String> characterList = newCharactersRequestDto.getCharacterList();
        List<MultipartFile[]> files = newCharactersImageRequestDto.getFiles();
        String title = newCharactersRequestDto.getTitle();
        //TODO: 로그인 유저 확인
        //TODO: characterList, files 길이 동일한지 확인
        //TODO: Story DB에 생성요청
        //Story newStory = Story.create(title, user);
        //TODO: Character DB에 생성요청 + 이미지 AI로 전달
        for(String characterName : characterList){
            //Character character = Character.create(story, characterName, imgUrl);
        }

        return new NewCharactersResponseDto();
    }
}
