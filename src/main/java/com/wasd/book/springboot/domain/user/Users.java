package com.wasd.book.springboot.domain.user;

import com.wasd.book.springboot.domain.BaseTimeEntity;
import com.wasd.book.springboot.domain.posts.Posts;
import jakarta.persistence.*;

import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Users extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Column
    private String nickname;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Posts> postsList;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public Users(String name, String email, String picture,String nickname,Role role){
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.nickname = nickname;
        this.role = role;
    }

    public Users update(String name,String nickname,String picture){
        this.name = name;
        this.nickname = nickname;
        this.picture = picture;

        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }

}
