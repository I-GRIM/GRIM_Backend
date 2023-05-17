package com.example.bookgrim.story.repository;

import com.example.bookgrim.story.domain.Page;
import com.example.bookgrim.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PageRepository extends JpaRepository<Page, String> {
    Optional<Page> findById(String id);

    @Query("SELECT p FROM Page as p WHERE p.story.id = :storyId ORDER BY p.page_order")
    List<Page> findByStoryId(@Param("storyId") String story);
}
