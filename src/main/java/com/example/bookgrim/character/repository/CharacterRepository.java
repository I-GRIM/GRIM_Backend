package com.example.bookgrim.character.repository;

import com.example.bookgrim.character.domain.Character;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacterRepository extends JpaRepository<Character, String> {
    Character findByName(String name);
}
