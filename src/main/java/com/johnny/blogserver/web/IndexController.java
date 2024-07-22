package com.johnny.blogserver.web;

import com.johnny.blogserver.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/{id}/{name}")
    public String index(@PathVariable Integer id,String name){

        System.out.println("------index--------");
        return "index";
    }
}
