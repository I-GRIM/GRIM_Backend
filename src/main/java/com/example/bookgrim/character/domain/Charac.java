package com.example.bookgrim.character.domain;
import com.example.bookgrim.common.domain.BaseEntity;
import com.example.bookgrim.story.domain.Story;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Getter
@Setter
@Entity
@DynamicInsert
@NoArgsConstructor
@Table(name = "charac")
public class Charac extends BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @Column(name = "img_url", length = 60, nullable = false)
    private String imgUrl;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    private Story story;

    public static Charac create(Story story, String name, String imgUrl){
        Charac charac = new Charac();
        charac.setName(name);
        charac.setImgUrl(imgUrl);
        charac.setStory(story);

        return charac;
    }
}
