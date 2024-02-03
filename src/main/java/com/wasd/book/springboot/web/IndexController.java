package com.wasd.book.springboot.web;

import com.wasd.book.springboot.config.auth.LoginUser;
import com.wasd.book.springboot.config.auth.dto.SessionUser;
import com.wasd.book.springboot.service.PostsService;
import com.wasd.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser users){
        model.addAttribute("posts",postsService.findAllDesc());

        if(users != null){
            model.addAttribute("LoginUserName",users.getUserName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id,Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);

        return "posts-update";
    }
}
