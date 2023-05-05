package com.example.bookgrim.story.repository;

import com.example.bookgrim.story.domain.Page;
import com.example.bookgrim.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PageRepository extends JpaRepository<Page, String> {
    Optional<Page> findById(String id);
}
