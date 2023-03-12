package com.wasd.book.springboot.config.auth.dto;

import com.wasd.book.springboot.domain.user.Users;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    SessionUser(){

    }
    public SessionUser(Users users){
        this.name = users.getName();
        this.email = users.getEmail();
        this.picture = users.getPicture();
    }
}
