package com.example.bookgrim.story.repository;


import com.example.bookgrim.story.domain.Page;
import com.example.bookgrim.story.domain.Story;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoryRepository extends JpaRepository<Story, String> {
    Optional<Story> findById(String id);
}
