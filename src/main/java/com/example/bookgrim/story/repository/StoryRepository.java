package com.example.bookgrim.story.repository;


import com.example.bookgrim.story.domain.Story;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRepository extends JpaRepository<Story, String> {
}
