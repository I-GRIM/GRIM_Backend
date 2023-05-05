package com.example.bookgrim.story.domain;

import com.example.bookgrim.common.domain.BaseEntity;
import com.example.bookgrim.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "tb_page")
public class Page extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name = "story_id",nullable = false)
    private Story story;

    @Column(name = "page_order", nullable = false)
    private int page_order;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "page_image_url")
    private String page_image_url;

    private Page(
            Story story,
            int page_order,
            String content,
            Status status,
            String page_iamge_url
    ){
        this.story = story;
        this.page_order = page_order;
        this.content = content;
        this.status = status;
        this.page_image_url = page_iamge_url;
    }

    public static Page of(
            Story story,
            int page_order,
            String content,
            Status status,
            String page_iamge_url
    ){
        return new Page(
                story,
                page_order,
                content,
                status,
                page_iamge_url
        );
    }
}
