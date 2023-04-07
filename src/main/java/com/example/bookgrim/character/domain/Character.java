package com.example.bookgrim.character.domain;
import com.example.bookgrim.common.domain.BaseEntity;
import com.example.bookgrim.story.domain.Story;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Getter
@Setter
@Entity
@DynamicInsert
@NoArgsConstructor
@Table(name = "character")
public class Character extends BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @Column(name = "img_url", length = 60, nullable = false)
    private String imgUrl;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "story_id", nullable = false)
    @ManyToOne
    private Story story;

    public static Character create(Story story, String name, String imgUrl){
        Character character = new Character();
        character.setName(name);
        character.setImgUrl(imgUrl);
        character.setStory(story);

        return character;
    }
}
