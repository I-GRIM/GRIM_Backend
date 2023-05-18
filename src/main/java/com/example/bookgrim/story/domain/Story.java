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
@Entity
@DynamicInsert
@NoArgsConstructor
@Table(name = "tb_story")
public class Story extends BaseEntity {


//    @ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name = "user_id",nullable = false)
    private User writer;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name="title_img")
    private  String title_img;;

    private Story(String title, User writer,Status status,String title_img){
        this.title = title;
        this.writer = writer;
        this.status = status;
        this.title_img = title_img;
    }
    public static Story of(
            String title,
            User writer,
            Status status,
            String title_img

    ){
        return new Story(
                title,
                writer,
                status,
                title_img
        );
    }

    public  void updateTitleImg(String title_img){
        this.title_img = title_img;
    }
}
