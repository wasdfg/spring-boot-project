package com.wasd.book.springboot.web.dto;

import com.wasd.book.springboot.domain.posts.Posts;
import com.wasd.book.springboot.domain.user.UserRepository;
import com.wasd.book.springboot.domain.user.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;

    private String nickname;

    @Builder
    public PostsSaveRequestDto(String title,String content,String nickname){
        this.title = title;
        this.content = content;
        this.nickname = nickname;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .build();
    }

}
