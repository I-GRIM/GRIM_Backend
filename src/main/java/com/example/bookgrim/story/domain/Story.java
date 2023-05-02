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
@Table(name = "tb_story")
public class Story extends BaseEntity {


//    @ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name = "user_id",nullable = false)
    private User user;

    @Column(name = "title", nullable = false)
    private String title;

}
