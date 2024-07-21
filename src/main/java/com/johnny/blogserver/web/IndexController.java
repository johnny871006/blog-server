package com.johnny.blogserver.web;

import com.johnny.blogserver.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/")
    public String index(){
//        int i = 9/0;
        String blog = null;
        if(blog == null){
            throw  new NotFoundException("此blog不存在");
        }
        return "index";
    }
}
