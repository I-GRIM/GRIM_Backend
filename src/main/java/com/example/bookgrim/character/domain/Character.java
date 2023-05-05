package com.example.bookgrim.character.domain;

import com.example.bookgrim.common.domain.BaseEntity;
import com.example.bookgrim.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "tb_character")
@NoArgsConstructor
public class Character extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name = "user_id",nullable = false)
    private User writer;

    @Column(name = "character_name", nullable = false)
    private String name;

    @Column(name = "image_url")
    private String imgUrl;

    private Character(
            User writer,
            String character_name,
            String image_url
    ){
        this.writer = writer;
        this.name = character_name;
        this.imgUrl = image_url;
    }

    public static Character of(
            User writer,
            String character_name,
            String image_url
    ){
        return new Character(
                writer,
                character_name,
                image_url
        );
    }
}
