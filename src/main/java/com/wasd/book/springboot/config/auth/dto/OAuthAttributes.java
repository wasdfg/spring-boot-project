package com.wasd.book.springboot.config.auth.dto;

import com.wasd.book.springboot.domain.user.Role;
import com.wasd.book.springboot.domain.user.Users;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class OAuthAttributes {
    private Map<String,Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    private String userName;

    @Builder
    public OAuthAttributes(Map<String,Object> attributes,String nameAttributeKey,String name,String email,String picture,String userName){
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.userName = userName;
    }

    public static OAuthAttributes of(String registrationId,String userNameAttributeName,Map<String,Object> attributes){
        if("naver".equals(registrationId)){

            return ofNaver("id",attributes);
        }

        return ofGoogle(userNameAttributeName,attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName,Map<String,Object> attributes){
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .userName((String) attributes.get("userName"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName,Map<String,Object> attributes){

        Map<String,Object> response= (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .userName((String) response.get("userName"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public Users toEntity(){
        return Users.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .userName(userName)
                .role(Role.USER)
                .build();
    }
}
