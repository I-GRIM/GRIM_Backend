package com.example.bookgrim.Character.domain;

import com.example.bookgrim.common.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "tb_character")
@NoArgsConstructor
public class Character extends BaseEntity {
    @Column(name = "user_id", nullable = false)
    private String user_id;

    @Column(name = "character_name", nullable = false)
    private String character_name;

    @Column(name = "image_url")
    private String image_url;

    private Character(
            String user_id,
            String character_name,
            String image_url
    ){
        this.user_id = user_id;
        this.character_name = character_name;
        this.image_url = image_url;
    }

    public static Character of(
            String user_id,
            String character_name,
            String image_url
    ){
        return new Character(
                user_id,
                character_name,
                image_url
        );
    }
}
