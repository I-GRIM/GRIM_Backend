package com.example.bookgrim.story.repository;


import com.example.bookgrim.story.domain.Page;
import com.example.bookgrim.story.domain.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StoryRepository extends JpaRepository<Story, String> {
    Optional<Story> findById(String id);

    @Query("SELECT s FROM Story s ORDER BY s.createdAt")
    List<Story> findAllDesc();

}