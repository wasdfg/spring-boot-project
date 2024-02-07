package com.wasd.book.springboot.domain.posts;

import com.wasd.book.springboot.domain.BaseTimeEntity;
import com.wasd.book.springboot.domain.user.Users;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@NoArgsConstructor
@Entity
@DynamicUpdate
public class Posts extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500,nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="users_nickname")
    private Users users;

    @Builder
    public Posts(String title,String content,Users users){
        this.title = title;
        this.content = content;
        if(users != null) {
            this.users = users;
        }
    }

    public void update(String title,String content){
        this.title = title;
        this.content = content;
    }
}
