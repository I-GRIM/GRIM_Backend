package com.example.bookgrim.character.repository;

import com.example.bookgrim.character.domain.Charac;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCharacterRepository extends JpaRepository<Charac, String> {
}
