package com.johnny.blogserver.web;

import com.johnny.blogserver.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){

        return "index";
    }

    @GetMapping("/about")
    public String about(){

        return "about";
    }

    @GetMapping("/blog")
    public String blog(){

        return "blog";
    }
}
