package com.example.bookgrim.character.repository;

import com.example.bookgrim.character.domain.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, String> {
}
