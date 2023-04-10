package com.example.bookgrim.story.domain;
import com.example.bookgrim.common.domain.BaseEntity;
import com.example.bookgrim.user.domain.User;
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
@Table(name = "story")
public class Story extends BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    private User user;

    public static Story create(String title, User user){
        Story story = new Story();
        story.setTitle(title);
        story.setUser(user);

        return story;
    }
}
