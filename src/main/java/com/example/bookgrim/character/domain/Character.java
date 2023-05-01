package com.example.bookgrim.character.domain;
import com.example.bookgrim.common.domain.BaseEntity;
import com.example.bookgrim.story.domain.Story;
import com.example.bookgrim.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;


@Getter
@Entity
@NoArgsConstructor
@Table(name = "tb_character")
public class Character extends BaseEntity {

    @Column(name = "img_url", length = 60, nullable = false)
    private String imgUrl;

    @Column(name = "character_name", nullable = false)
    private String name;

    @Column(name = "user_id", nullable = false)
    private String writer;

//    @ManyToOne
//    private Story story;

    public Character(
            String writer,
            String name,
            String imgUrl
    ){
        this.writer = writer;
        this.name = name;
        this.imgUrl = imgUrl;


    }

    public static Character of(
            String writer,
            String name,
            String imgUrl
    ){
        return new Character(
                writer,
                name,
                imgUrl
        );
    }
//    public void create(
//            String name,
//            String imgUrl
//    ){
//        Character charac = new Character();
//        charac.setName(name);
//        charac.setImgUrl(imgUrl);
//        charac.setStory(story);
//
//    }
}
